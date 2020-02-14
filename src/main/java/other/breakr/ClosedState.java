package other.breakr;

public class ClosedState extends AbstractBreakerState {

    public ClosedState(BreakerManager manager) {
        super(manager);

        //重置失败计数器
        manager.resetFailureCount();
    }

    @Override
    public void ActUponException() {
        super.ActUponException();

        //如果失败次数达到阈值，则切换到断开状态
        if (manager.failureThresholdReached()) {
            manager.moveToOpenState();
        }
    }
}