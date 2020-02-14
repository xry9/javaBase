package other.breakr;


public class HalfOpenState extends AbstractBreakerState {

    public HalfOpenState(BreakerManager manager) {
        super(manager);

        //重置连续成功计数
        manager.resetConsecutiveSuccessCount();
    }

    @Override
    public void ActUponException() {
        super.ActUponException();

        //只要有失败，立即切换到断开模式
        manager.moveToOpenState();
    }

    @Override
    public void protectedCodeHasBeenCalled() {
        super.protectedCodeHasBeenCalled();

        //如果连续成功次数达到阈值，切换到闭合状态
        if (manager.consecutiveSuccessThresholdReached()) {
            manager.moveToClosedState();
        }
    }
}