package info.setmy.ipfs;

import io.ipfs.api.IPFS;
import io.ipfs.multihash.Multihash;

import java.io.IOException;

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

    public byte[] getContent(String string) {
        Multihash filePointer = Multihash.fromBase58(string);
        try {
            byte[] fileContents = ipfs.cat(filePointer);
            return fileContents;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
