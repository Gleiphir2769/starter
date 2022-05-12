import org.apache.bookkeeper.client.BKException;
import org.apache.bookkeeper.client.BookKeeper;
import org.apache.bookkeeper.client.LedgerHandle;
import org.apache.zookeeper.KeeperException;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws BKException {
        try {
            String connectionString = "10.238.18.141:2181"; // For a single-node, local ZooKeeper cluster
            BookKeeper bkClient = new BookKeeper(connectionString);
            byte[] password = "some-password".getBytes();
            LedgerHandle handle = bkClient.createLedger(BookKeeper.DigestType.MAC, password);
            long entryId = handle.addEntry("Some entry data".getBytes());
            System.out.format("id:(%d, %d)", handle.getId(), entryId);
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }
}
