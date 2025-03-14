# Bowling Game Polyglot

A demonstration of TDD: The classic [Bowling Game Kata](http://butunclebob.com/files/downloads/Bowling%20Game%20Kata.ppt) 
written in multiple languages, with a twist.  They all support a new feature:

When scoring a game, the score for each frame must be sent to an observer. 
The observer may be a screen that needs to display the scores.  Or it may be
a sales system, that charges by the frame.  It doesn't really matter.  What does
matter is that the observer is notified of the score of each frame when the score 
is calculated.

## Clojure
```bash
    cd clojure
    lein spec
    lein spec -a  # autorun
```
Clojure demonstrates the power of dynamic types, and a-la-carte polymorphism.
We use a `multimethod` to declare the abstraction `notify-frame`.  A default
"null" implementation is provided.

In the tests, we use `with-redefs` to *stub* out the implementation using **speclj**'s
`stub` feature.  This maintains elegant test structure.

## Java
```bash
    cd java
    mvn test
```
Java, being a statically typed, object-oriented language, allows us to flex our design
pattern chops.

First we declare a `FrameObserver` interface to establish the **Observer Patter**.  Then we can use the **Null Object** 
pattern declaring a `NullFrameObserver` to handle the default case.  Finally, a `MochFrameObserver` 
is needed for testing.  

Yes, we hand-roll our mocks because that is what craftsmen do in statically typed languages.

## Typescript
```bash
    cd typescript
    npm run test
```
Typescript aspires to be statically typed as well, so the result is similar to Java's. 
3 new files with 3 new classes.

## Python

**TODO**

Although Python is dynamically typed, more recent convention is to use the `ABC` base class
forcing a more statically typed structure.  The result is similar to Java, although duck-typing 
allows some creativity.

Using Python's **unittest.mock** we can forego implementing `MockFrameObserver` without 
sacrificing good test structure and without duplicating code.

## Ruby

**TODO**

Ruby devs tend to embrace duck-typing more than Python devs.  This solution has
a `NullFrameObserver`.  We also have `MockFrameObserver` because RSpec's "Test Doubles"
violate elegance of the "BOC"/"AAA"/"Given, When, Then" pattern.  But the `FrameObserver` 
would likely be omitted.