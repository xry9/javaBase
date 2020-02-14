package other;

public class FalseSharingDemo1 {
    // 测试使用线程数
    private final static  int NUM_THREADS= 4;
    // 测试次数
    private final static  int NUM_TEST_TIMES= 1;

    // 无填充 无缓存行对齐的对象类 普通热变量
    static class PlainHotVariable {
        // 1个long 类型变量 占用内存 1*8 = 8 字节，
        public volatile long value = 0L;
    }

    // 有填充 有缓存行对其的对象类
    static class AlignHotVariable extends PlainHotVariable{
        // 用于填充，6个long 类型的变量 ，总占用内存为 6*8 = 48 字节
        // 加上继承父类的一个变量value，那么总共该对象 占用内存为：8字节对象头 + 8字节父类变量+ 6*8字节填充变量 = 64字节，
        // 正好满足一个对象全部在一个缓存行中，消除了伪竞争问题
        public long p1,p2,p3,p4,p5,p6;
    }

    // 竞争者
    static final class CompetitorThread extends Thread {
        //迭代次数
        private final static long ITERATIONS = 500L * 1000L * 1000L;
        private PlainHotVariable plainHotVariable;
        public CompetitorThread(final PlainHotVariable plainHotVariable) {
            this.plainHotVariable = plainHotVariable;
        }
        @Override
        public void run() {
            for (int i=0;i<ITERATIONS;i++){
                plainHotVariable.value = i;
            }
        }
    }

    public static long runOneTest(PlainHotVariable[] plainHotVariables) throws Exception{
        //开启多个线程进行测试
        CompetitorThread[] competitorThreads = new CompetitorThread[plainHotVariables.length];
        for (int i = 0; i < plainHotVariables.length; i++) {
            competitorThreads[i] = new CompetitorThread(plainHotVariables[i]);
        }
        final long start = System.nanoTime();
        for (Thread thread : competitorThreads){
            thread.start();
        }
        for (Thread thread : competitorThreads){
            thread.join();
        }
        return System.nanoTime() - start;
    }


    public static boolean runOneCompare(int threadNum)throws Exception{
        PlainHotVariable[] plainHotVariables = new PlainHotVariable[threadNum];
        for (int i = 0; i < threadNum; i++) {
            plainHotVariables[i] = new PlainHotVariable();
        }
        // 进行无填充 无缓存行对齐测试
        long t1 = runOneTest(plainHotVariables);
        AlignHotVariable[] alignHotVariables = new AlignHotVariable[threadNum];

        for (int i = 0; i < NUM_THREADS; i++) {
            alignHotVariables[i] = new AlignHotVariable();
        }
        // 进行填充 有缓存行对齐的测试
        long t2 = runOneTest(alignHotVariables);

        System.out.println("无填充 无缓存行对齐Plain："+ t1);
        System.out.println("有填充 有缓存行对齐Plain："+ t2);

        // 返回结果对比
        return t1 > t2;
    }
    public static void runOneSuit(int threadsNum, int testNum) throws Exception{
        int expectedCount = 0;
        for (int i = 0; i < testNum; i++) {
            if (runOneCompare(threadsNum)){
                expectedCount++;
            }
        }
        //计算有填充 有缓存对其的测试场景下响应时间更短的概率
        System.out.println("Radio (Plain < Align ):" + expectedCount * 100D / testNum +"%");
    }
    public static void main(String[] args) throws Exception{
        runOneSuit(NUM_THREADS, NUM_TEST_TIMES);
    }
}
