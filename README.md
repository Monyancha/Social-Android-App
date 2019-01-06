### Background

Social App was written with one big goal: showcase material design in Android in a real application. While the app successfully achieved its goal, from an architecture point of view, it lacks all features that would make it a modular, scalable, testable and maintainable app: with UI logic in Android classes, no tests and only one module. 
The App represents a great real world app example: it provides a fairly complex set of functionalities, it has technical debt, it has features that have to be dealt with as APIs are being removed.
All of these problems are encountered by many projects in the Android community and therefore, make the app a suitable showcase for all the advantages that architecture components bring. 


### Android Studio IDE setup

Plaid requires Android Studio version 3.4 or higher.

Plaid uses [ktlint](https://ktlint.github.io/) to enforce Kotlin coding styles.
Here's how to configure it for use with Android Studio (instructions adapted
from the ktlint [README](https://github.com/shyiko/ktlint/blob/master/README.md)):

- Close Android Studio if it's open
- Download ktlint using these [installation instructions](https://github.com/shyiko/ktlint/blob/master/README.md#installation)

- Inside the project root directory run:

  `./ktlint --apply-to-idea-project --android`

- Remove ktlint if desired:

  `rm ktlint`

- Start Android Studio

---



<img src="screenshots/plaid_demo.gif" width="300" align="right" hspace="20">

