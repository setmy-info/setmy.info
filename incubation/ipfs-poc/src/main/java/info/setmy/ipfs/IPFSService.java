package info.setmy.ipfs;

import io.ipfs.api.IPFS;
import io.ipfs.api.MerkleNode;
import io.ipfs.api.NamedStreamable;
import io.ipfs.multihash.Multihash;

import java.io.File;
import java.io.IOException;

import static java.nio.charset.StandardCharsets.UTF_8;

public class IPFSService {

    private static final IPFSService IPFS_SERVICE = new IPFSService();

    private IPFS ipfs;

    public static IPFSService getInstance() {
        return IPFS_SERVICE;
    }

    public void init() {
        ipfs = new IPFS("/ip4/127.0.0.1/tcp/5001");
        try {
            ipfs.refs.local();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String asString(String hash) {
        return new String(getContent(hash), UTF_8);
    }

    public byte[] getContent(String hash) {
        Multihash filePointer = Multihash.fromBase58(hash);
        try {
            byte[] fileContents = ipfs.cat(filePointer);
            return fileContents;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String addFile(String filePath) {
        NamedStreamable.FileWrapper file = new NamedStreamable.FileWrapper(new File(filePath));
        try {
            MerkleNode addResult = ipfs.add(file).get(0);
            System.out.println(addResult);
            System.out.println("HASH: " + addResult.hash);
            return addResult.hash.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
