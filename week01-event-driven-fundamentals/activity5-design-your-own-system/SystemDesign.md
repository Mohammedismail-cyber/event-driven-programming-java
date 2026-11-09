# Event-Driven System Design

## System Name: Smart Home Controller

---

## Part 1: Event Sources
1. Light switch / lights toggle - generates "lights on" and "lights off" events
2. Security panel - generates "armed" and "disarmed" events
3. Door sensor - generates "door opened" and "door closed" events
4. Motion sensor - generates "motion detected" events
5. Thermostat / temperature sensor - generates "temperature changed" events

---

## Part 2: Events

| Event Name | Source | Description |
|---|---|---|
| LightsToggled | Lights toggle | The user switches the lights on or off |
| SecurityArmed / SecurityDisarmed | Security panel | The user arms or disarms the alarm |
| DoorOpened | Door sensor | The door contact opens |
| MotionDetected | Motion sensor | Movement is seen in a room |
| TemperatureChanged | Thermostat | Temperature goes above or below the set value |

---

## Part 3: Event Handlers

| Event | Handler Action |
|---|---|
| LightsToggled | Update the lights state, show "Lights are on/off" and log it |
| SecurityArmed / SecurityDisarmed | Change the security state, log it, clear any active alarm |
| DoorOpened | Log it. If security is armed, raise the ALARM (red screen); if not, just show "Door opened" |
| MotionDetected | If armed, raise the alarm; if it is dark and disarmed, turn the lights on |
| TemperatureChanged | Switch the heater or fan on or off to reach the target temperature |

---

## Part 4: Event Flow Diagram

```
 User / Sensor        Event Source        Event Object       Event Queue
 (door opens)  --->  Door sensor   --->  DoorOpened   --->  [ ... ][ DoorOpened ]
                                                                  |
                                                                  v
                                                            Event Loop (EDT)
                                                                  |
                                                    takes one event at a time
                                                                  v
                                                          Handler (listener)
                                                     armed?  -- yes --> ALARM
                                                             -- no  --> log only
                                                                  |
                                                                  v
                                                       GUI update (status + log)
```

---

## Part 5: Potential Issues
1. **A slow handler freezes the system** (e.g. sending an email alert on the EDT) - run slow work on a background thread and update the GUI with `invokeLater`.
2. **Many sensor events at once flood the queue** (motion sensor firing constantly) - ignore duplicates for a short time (debouncing) or batch the events.
3. **A handler fails and the alarm never triggers** - wrap handlers in try/catch, log the error, and add a fallback alert.
