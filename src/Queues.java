import java.util.concurrent.BlockingQueue;

public class Queues {
    private BlockingQueue<byte[]> bobQueue;
    private BlockingQueue<byte[]> alexQueue;
    public Queues(BlockingQueue<byte[]> bobQueue, BlockingQueue<byte[]> alexQueue) {
        this.bobQueue = bobQueue;
        this.alexQueue = alexQueue;
    }

    public BlockingQueue<byte[]> getBobQueue() {
        return bobQueue;
    }

    public BlockingQueue<byte[]> getAlexQueue() {
        return alexQueue;
    }

}
