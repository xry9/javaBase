package other.breakr;


public class BreakerManager {

    public int failureCount; //失败次数
    public int consecutiveSuccessCount; //连续成功次数
    public int failureThreshold; //最大调用失败次数
    public int consecutiveSuccessThreshold; //连续调用成功次数
    public int timeout;

    private AbstractBreakerState state; //当前熔断器状态

    public boolean isClosed() {
        return state instanceof ClosedState;
    }

    public boolean isOpen() {
        return state instanceof OpenState;
    }

    public boolean isHalfOpen() {
        return state instanceof HalfOpenState;
    }

    protected void moveToClosedState() {
        state = new ClosedState(this);
    }

    protected void moveToOpenState() {
        state = new OpenState(this);
    }

    protected void moveToHalfOpenState() {
        state = new HalfOpenState(this);
    }

    protected void increaseFailureCount() {
        failureCount++;
    }

    public void resetFailureCount() {
        failureCount = 0;
    }

    protected boolean failureThresholdReached() {
        return failureCount >= failureThreshold;
    }

    protected void increaseSuccessCount() {
        consecutiveSuccessCount++;
    }

    protected void resetConsecutiveSuccessCount() {
        consecutiveSuccessCount = 0;
    }

    protected boolean consecutiveSuccessThresholdReached() {
        return consecutiveSuccessCount >= consecutiveSuccessThreshold;
    }

    /**
     * Close状态下最大失败次数，HalfOpen状态下使用的最大连续成功次数，以及Open状态下的超时时间
     * 在初始状态下，熔断器切换到闭合状态
     *
     * @param failureThreshold
     * @param consecutiveSuccessThreshold
     * @param timeout
     */
    public BreakerManager(int failureThreshold, int consecutiveSuccessThreshold, int timeout) {
        if (failureThreshold < 1 || consecutiveSuccessThreshold < 1) {
            throw new RuntimeException("熔断器闭合状态的最大失败次数和半熔断状态的最大连续成功次数必须大于0！");
        }
        if (timeout < 1) {
            throw new RuntimeException("熔断器断开状态超时时间必须大于0！");
        }
        this.failureThreshold = failureThreshold;
        this.consecutiveSuccessThreshold = consecutiveSuccessThreshold;
        this.timeout = timeout;
        moveToClosedState();
    }

    /**
     * 该方法用于测试
     * 通过AttempCall调用，传入期望执行的代理方法，该方法的执行受熔断器保护。这里使用了锁来处理并发问题
     */
//    https://www.cnblogs.com/jager/p/6253166.html
    //加锁在多线程环境下是不能容忍的呀，想想能不能通过引用类型的原子操作克服
    public void attemptCall(boolean rs, int times) {
        for (int i = 0; i < times; i++) {
            //需要加同步锁
            state.protectedCodeIsAboutToBeCalled();
            try {
                //调用服务
                if (!rs) {
                    throw new Exception();
                } else {
                    System.out.println("第" + (i + 1) + "服务调用成功！");
                }
            } catch (Exception e) {
                //需要加同步锁
                System.out.println("第" + (i + 1) + "服务调用超时！");
                state.ActUponException();
            }
            //需要加同步锁
            state.protectedCodeHasBeenCalled();
        }
    }

    /**
     * 手动切换到闭合状态
     */
    public void close() {
        //需要加同步锁
        moveToClosedState();
    }

    /**
     * 手动切换到断开状态
     */
    public void open() {
        //需要加同步锁
        moveToOpenState();
    }
}