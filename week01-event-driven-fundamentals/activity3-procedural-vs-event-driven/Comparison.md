# Activity 3 - Procedural vs Event-Driven

(Draft answers. Run both programs first and check these against what you observed.)

## ProceduralCalculator questions
1. **Order of steps:** read first number, read operator, read second number,
   calculate with a switch, print the result, end.
2. **Wrong input at step 2:** an invalid operator prints "Invalid operator" and the
   program ends. (Typing letters where a number is expected crashes it with an
   `InputMismatchException`.) The user cannot go back and correct it.
3. **Calculate again without restarting?** No. The program ends after one calculation.
4. **Who controls the flow?** The program. It decides what is asked and in what order.

## EventDrivenCalculator questions
1. **Buttons in any order?** Yes. The user decides what to click next.
2. **Mistake?** Press **C** to clear and start again, or just keep typing a new number.
3. **Calculate again without restarting?** Yes, as many times as you like.
4. **Who controls the flow?** The user. The program waits for events and reacts.
5. **How many event handlers?** There are 4 kinds of handler: digits, operators,
   equals and clear. Counting every button object, that is 16 ActionListeners
   (10 digits 0-9, 4 operators, 1 equals, 1 clear).

## Comparison table

| ASPECT | PROCEDURAL | EVENT-DRIVEN |
|---|---|---|
| Who controls the flow? | The program (fixed script) | The user (events decide) |
| Can user make mistakes and recover easily? | No: restart the program | Yes: click Clear and continue |
| Can user interact in any order? | No: one fixed sequence | Yes: any order |
| How many possible execution paths? | One main path (plus error exits) | Many: depends on the clicks |
| Code organization | `main()` does everything top to bottom | Factory methods + separate handlers |
| User experience | Console, rigid | GUI, flexible |

## Reflection
The main difference is who is in control. In the procedural calculator the program
runs a fixed script and the user can only answer its questions in order, while in
the event-driven calculator the program waits and reacts to whatever the user does.
The event-driven version lets the user fix mistakes with one click, calculate again
without restarting and use the buttons in any order. For interactive applications I
prefer the event-driven approach because real users do not behave in a fixed order.
Event-driven programming is better for GUIs because a GUI must respond to many
unpredictable actions (clicks, typing, resizing) at any moment. Procedural code
is still fine for simple scripts and batch jobs that run once from start to finish.
