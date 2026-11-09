# Activity 1 - Spot the Components

(Draft answers. Watch the videos and change or add rows so they reflect what YOU saw.)

| System | Event Source | Event | Listener | Handler / Action |
|---|---|---|---|---|
| ATM Machine | Keypad button | Button pressed | ATM software | Verify PIN |
| ATM Machine | Card slot | Card inserted | Card reader | Read card data |
| ATM Machine | Cash dispenser | Cash taken | Sensor | End transaction |
| Microwave Oven | Door sensor | Door opened | Safety interlock | Stop cooking |
| Microwave Oven | Start button | Start pressed | Microwave controller | Turn on magnetron, start countdown |
| Microwave Oven | Timer | Time expires | Microwave controller | Stop cooking, beep |
| Traffic Light | Timer | Time expires | Traffic controller | Change lights (green -> yellow -> red) |
| Traffic Light | Pedestrian button | Button pressed | Traffic controller | Schedule a pedestrian crossing phase |
| Video Game | Keyboard | Key pressed (arrow / space) | Input manager | Move or jump the character |
| Video Game | Collision detector | Player touches enemy | Game engine | Reduce health, play sound |
| Video Game | Game timer | Frame tick (60 per second) | Game loop | Update positions, redraw screen |
| Elevator | Hall call button | Button pressed | Elevator controller | Dispatch the nearest car |
| Elevator | Floor button (inside) | Floor selected | Elevator controller | Add floor to the stop list |
| Elevator | Door sensor | Beam blocked | Door controller | Reopen the doors |
| Smartphone touchscreen | Touch screen | Tap / swipe | Operating system (touch driver) | Open app, scroll the page |
| Online shopping website | "Buy" button | Button clicked | Order service | Process payment |
| Self-checkout at supermarket | Barcode scanner | Item scanned | Checkout software | Look up price, add to cart |
| Self-checkout at supermarket | Weighing scale | Weight changed | Checkout software | Verify the weight, warn if wrong |

Systems analysed: ATM, microwave, traffic light, video game, elevator, smartphone,
online shopping website, self-checkout (8 systems).

## Self-check
1. **Event source vs event listener:** the source is the object that GENERATES the
   event (a button, a sensor, a timer). The listener is the object that WAITS for
   it and receives it, then runs the handler code.
2. **One source, many listeners? Yes.** Example: a "Buy" button on a website can
   notify the payment service, the inventory service and the email service.
3. **One listener, many sources? Yes.** Example: one ActionListener shared by the
   Add, Subtract and Reset buttons, using `e.getSource()` to tell them apart. The
   ATM software also listens to the keypad, card reader and cash dispenser.
