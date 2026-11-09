# Activity 1 - Self-Check (draft answers)

1. **Role of the Subject interface:** it defines the contract every publisher
   must follow (register, remove, notify), so observers depend on an interface
   and not on one concrete class.
2. **notifyObservers() with no observers:** nothing happens; the loop runs zero
   times and there is no error.
3. **register vs remove:** `registerObserver` adds an observer to the list so it
   starts receiving updates; `removeObserver` takes it out so it stops.
4. **Why an instance variable:** the list must live as long as the subject and be
   shared between method calls (register in one call, use in another). A local
   variable would disappear when the method ended.
