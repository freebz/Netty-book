// 예제 5-1 네티의 이벤트 루프

... package, import 생략 ...

public abstract class SingleThreadEventExecutor extends AbstractEventExecutor {

... 생략 ...

   private final Queue<Runnable> taskqueue;

... 생략 ...

   protected Queue<Runnable> newTaskQueue() {
      return new LinkedBlockingQueue<Runnable>();
   }

... 생략 ...

   protected Runnable pollTask() {
      assert inEventLoop();
      for (;;) {
	 Runnable task = taskQueue.poll();
	 if (task == WAKEUP_TASK) {
	    continue;
	 }

	 return task;
      }
   }

... 생략 ...

   protected boolean runAllTasks() {
      fetchFromDelayedQueue();
      Runnable task = pollTask();
      if (task == null) {
	 return false;
      }

      for (;;) {
	 try {
	    task.run();
	 } catch (Throwable t) {
	    logger.warn("A task raised an exception.", t);
	 }

	 task = pollTask();
	 if (task == null) {
	    lastExecutionTime = ScheduledFutureTask.nanoTime();
	    return true;
	 }
      }
   }
... 생략 ...
}
