// 예제 5-2 특별 케이크 주문

public class SpecialCake {

   public static void main(String[] args) {

      Bakery bakery = new Bakery();

      // 케이크를 주문하고 주문서를 받는다.
      Future future = bakery.orderCake();

      // 다른 일을 한다.
      ...
      doSomething();
      ...

      // 케이크가 완성되었는지 확인한다.
      if (future.isDone()) {
	 Cake cake = future.getCake();
      }
      else{
	 // 케이크가 완성되었는지 확인한다.
	 while (future.isDone != true) {
	    // 다른 일을 한다.
	    ...
	    doSomething();
	    ...
	 }

	 // while 루프를 빠져나왔으므로 완성된 케이크를 가져온다.
	 Cake cake = future.getCake();
      }
   }
}
