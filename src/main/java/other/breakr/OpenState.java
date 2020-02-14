package other.breakr;

import java.util.Timer;
import java.util.TimerTask;

public class OpenState extends AbstractBreakerState {

    public OpenState(BreakerManager manager) {
        super(manager);

        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                timeoutHasBeenReached();
                timer.cancel();
            }
        }, manager.timeout);
    }

    @Override
    public void protectedCodeIsAboutToBeCalled() {
        super.protectedCodeIsAboutToBeCalled();
        throw new RuntimeException("服务已熔断，请稍等重试！");
    }

    /**
     * 断开超过设定的阈值，自动切换到半断开状态
     */
    private void timeoutHasBeenReached()
    {
        manager.moveToHalfOpenState();
    }
}