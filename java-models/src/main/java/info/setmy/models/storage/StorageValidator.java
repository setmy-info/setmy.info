package info.setmy.models.storage;

/**
 * @author <a href="mailto:imre.tabur@mail.ee">Imre Tabur</a>
 */
interface StorageValidator {

    public String validateFileName(String directoryLocation);

    public String validateUperAndHomeChange(String directoryPath);
}
