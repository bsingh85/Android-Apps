# Test Plan

### Version History

<table class="tg" style="border: solid 1px;border-collapse:collapse;">
  <tr>
    <th style="border: solid 1px">Version</th>
    <th style="border: solid 1px">Description</th>
    <th style="border: solid 1px">Details</th>
  </tr>  <tr>       <td style="border: solid 1px">1.0</td>      <td style="border: solid 1px">Test Plan for alpha version</td>      <td style="border: solid 1px">Initial Test Plan</td>  </tr>  <tr>
       <td style="border: solid 1px">2.0</td>
      <td style="border: solid 1px">Final Test Plan</td>
      <td style="border: solid 1px">         <ul>             <li>Added Test results for completed tests. </li><li>Added JUnit test cases. </li><li>Added automated tests.</li>       </ul></td>
  </tr></table>

**Author**: Team 10
* Bharati Singh, bsingh60
* Hao Luo, hluo33
* Nathan Turnbow, nturnbow3
* Rajan Jethva, rjethva3

## 1 Testing Strategy

### 1.1 Overall strategy

Team 10 will start unit testing during the app development. We will use Espresso framework to write Android UI test cases. We will write JUnit Test cases to test the backend/Java code. Writing unit tests will be individual developer’s responsibility. Peer review will verify the % of code coverage before approving the pull request.
Once alpha version of app is built we will start the system testing where we will run this app on multiple android emulators which will have different resolutions and hardware footprints.
In the alpha version of app each individual team member will run this app on their android device by building APK and installing it on real android hardware.
Team will also design regression test suite in Espresso after alpha version of the app is available to the users. Regression tests will be performed on every subsequent feature release.


### 1.2 Test Selection

Test cases will be carried out through white-box JUnit tests in all cases where the requirement is fully captured in the logic layer of our program. Where the requirement is more complex and cannot be fully captured in a logical unit, we will use Espresso coded UI tests to ensure the requirement. Wherever a requirement can be captured both within the logic *and* redundantly in the user interface, both forms of testing will be implemented. Finally, for any requirements which cannot be fully captured by a coded UI test (ex: a requirement that requires confirming a negative like proving that something does *not* exist or proving that something cannot be seen) will be tested through black-box manual testing of the application. Black-box manual testing will also be employed to ensure that the user experience of the app is favorable and that the app is easily navigable and usable at different screen sizes.

### 1.3 Adequacy Criterion

For all JUnit-based tests, test-quality will be measured by line and branch coverage metrics and will be expected to approximate 100%.

For all Espresso-based tests, test quality will be measured by element coverage metrics (ex: how many text-boxes, buttons, drop-down options, etc. were interacted with). Test quality will also be measured in terms of functional adherence to all use cases - in other words, there must be a coded-UI test to cover each isolated use case a user might want to perform against the application. Any coded-UI test against a customer-impacting use case will be expected to always pass at 100%.

The Espresso-based tests will always start with a user login and test an isolated use case, but will not be able to test every interaction between isolated use cases (ie: removing a quiz and then subsequently adding a quiz with the same name). To ensure that bugs are caught which require a complex interaction between use-cases, we will be employing manual testing. Success of such testing will be measured in terms of time spent after all other tests are passing sufficiently.

Additionally, responsiveness of the application will be measured using a mixture of JUnit and Espresso tests which employ timers and which will maintain a standard. For example, if a database call is found to take 300 milliseconds, the test will consider the test failed if the same call is suddenly found to take a full second. The expectation will be that no incoming change will cause a customer-facing use case to be executed more slowly, particularly if the slow-down is human-perceptible.

### 1.4 Bug Tracking

Team 10 will be using Github Issues to track bugs and enhancements. We will create below labels to separate bugs and enhancements.

- Bug Labels
  - Functional Bug - Label indicating the functionality or component which is impacted by the bug.
  - Non-Functional Bug - Label indicating the process, view, or user experience which is impacted by the bug.
  - Priority1 (Urgent)
  - Priority2 (Next Scheduled Release)
  - Priority3 (Nice to have)

- Enhancements Labels
  - Revenue-Generating
  - Performance
  - Customer-Requested
  - Engineer-Suggested
  - Questions

### 1.5 Technology

- JUnit – For unit testing of Java Code (Unit testing)
- Espresso – For automated Android UI testing (Integration Testing, UI testing, UX testing)
- JUnit with programmatic timers – For app performance test

## 2 Test Cases

<table class="tg" style="border: solid 1px;border-collapse:collapse;">
  <tr>
    <th style="border: solid 1px">Test Case</th>
    <th style="border: solid 1px">Category</th>
    <th style="border: solid 1px">Purpose</th>
    <th style="border: solid 1px">Steps</th>
    <th style="border: solid 1px">Expected Result</th>
    <th style="border: solid 1px">Actual Result</th>
    <th style="border: solid 1px">Status</th>
    <th style="border: solid 1px">Comments</th>
  </tr>
  <tr>
    <td style="border: solid 1px">1</td>
    <td style="border: solid 1px">Registration</td>
    <td style="border: solid 1px">Successfully register a new user</td>
    <td style="border: solid 1px">
        <ol>
            <li>Start the application</li>
            <li>Select the “REGISTER” button</li>
            <li>Enter valid form data</li>
            <li>Select the “REGISTER” button</li>
            <li>Confirm that the newly created user can log in</li>
        </ol>
   </td>
    <td style="border: solid 1px">A new user is created and can log in</td>
    <td style="border: solid 1px">A new user is created and can log in</td>
    <td style="border: solid 1px">Pass</td>
    <td style="border: solid 1px"></td>
  </tr>
  <tr>
    <td style="border: solid 1px">2</td>
    <td style="border: solid 1px">Registration</td>
    <td style="border: solid 1px">Failure to register - username already exists</td>
    <td style="border: solid 1px">
        <ol>
            <li>Start the application</li>
            <li>Select the “REGISTER” button</li>
            <li>Enter valid form data except select a username which already exists</li>
            <li>Select the “REGISTER” button</li>
            <li>Confirm that an error message is displayed</li>
        </ol>
</td>
    <td style="border: solid 1px">An error message is displayed</td>
    <td style="border: solid 1px">An error message is displayed</td>
    <td style="border: solid 1px">Pass</td>
    <td style="border: solid 1px"></td>
  </tr>
  <tr>
    <td style="border: solid 1px">3</td>
    <td style="border: solid 1px">Registration</td>
    <td style="border: solid 1px">Failure to register - empty username</td>
    <td style="border: solid 1px">
        <ol>
            <li>Start the application</li>
            <li>Select the “REGISTER” button</li>
            <li>Enter valid form data except leave the username field blank</li>
            <li>Select the “REGISTER” button and confirm that a field error message is displayed</li>
            <li>Confirm with a unit test: a null or empty string submitted at the logic layer throws an exception</li>
        </ol>
    </td>
    <td style="border: solid 1px">An error message is displayed</td>
    <td style="border: solid 1px">An error message is displayed</td>
    <td style="border: solid 1px">Pass</td>
    <td style="border: solid 1px"></td>
  </tr>
  <tr>
    <td style="border: solid 1px">4</td>
    <td style="border: solid 1px">Registration</td>
    <td style="border: solid 1px">Failure to register - username too long</td>
    <td style="border: solid 1px">
        <ol>
            <li>Start the application</li>
            <li>Select the “REGISTER” button</li>
            <li>Enter valid form data except enter a username which is too long</li>
            <li>Select the “REGISTER” button and confirm that a field error message is displayed</li>
            <li>Using a unit test, ensure that any too long username is met with an exception and is not accepted into the database</li>
        </ol>
</td>
    <td style="border: solid 1px">An error message is displayed</td>
    <td style="border: solid 1px">An error message is displayed</td>
    <td style="border: solid 1px">Pass</td>
    <td style="border: solid 1px"></td>
  </tr>
  <tr>
    <td style="border: solid 1px">5</td>
    <td style="border: solid 1px">Registration</td>
    <td style="border: solid 1px">Failure to register - username with non-printable characters</td>
    <td style="border: solid 1px">
        <ol>
            <li>Start the application</li>
            <li>Select the “REGISTER” button</li>
            <li>Enter valid form data except enter a username which contains non-printable characters</li>
            <li>Select the “REGISTER” button and confirm that a field error message is displayed</li>
            <li>Using a unit test, ensure that a username with non-printable characters is met with an exception and the registration is not accepted into the database</li>
        </ol>
    </td>
    <td style="border: solid 1px">An error message is displayed</td>
    <td style="border: solid 1px">An error message is displayed</td>
    <td style="border: solid 1px">Pass</td>
    <td style="border: solid 1px"></td>
  </tr>
  <tr>
    <td style="border: solid 1px">6</td>
    <td style="border: solid 1px">Registration</td>
    <td style="border: solid 1px">UI: when required information is missing, an error message is displayed</td>
    <td style="border: solid 1px">
        <ol>
            <li>Start the application</li>
            <li>Select the “REGISTER” button</li>
            <li>Leave a required field empty</li>
            <li>Select the “REGISTER” button and confirm that a field error message is displayed</li>
        </ol>
    </td>
    <td style="border: solid 1px">An error message is displayed</td>
    <td style="border: solid 1px">An error message is displayed</td>
    <td style="border: solid 1px">Pass</td>
    <td style="border: solid 1px"></td>
  </tr>
  <tr>
    <td style="border: solid 1px">7</td>
    <td style="border: solid 1px">Registration</td>
    <td style="border: solid 1px">UI: when email does not conform to the right format, an error message is displayed</td>
    <td style="border: solid 1px">
        <ol>
            <li>Start the application</li>
            <li>Select the “REGISTER” button</li>
            <li>Enter valid form data except enter an email which is not in the correct format (one @ symbol preceded and followed by alphanumeric characters) </li>
            <li>Select the “REGISTER” button and confirm that an error message is displayed on the field</li>
        </ol>
    </td>
    <td style="border: solid 1px">An error message is displayed</td>
    <td style="border: solid 1px">An error message is displayed</td>
    <td style="border: solid 1px">Pass</td>
    <td style="border: solid 1px"></td>
  </tr>
  <tr>
    <td style="border: solid 1px">8</td>
    <td style="border: solid 1px">Login</td>
    <td style="border: solid 1px">Successful login</td>
    <td style="border: solid 1px">
        <ol>
            <li>Start the application</li>
            <li>Enter a valid username</li>
            <li>Click the “LOGIN” button</li>
            <li>Confirm that the "Home" page is presented</li>
        </ol>
    </td>
    <td style="border: solid 1px">User is redirected to the home page</td>
    <td style="border: solid 1px">User is redirected to the home page</td>
    <td style="border: solid 1px">Pass</td>
    <td style="border: solid 1px"></td>
  </tr>
  <tr>
    <td style="border: solid 1px">9</td>
    <td style="border: solid 1px">Login</td>
    <td style="border: solid 1px">Failure to login - username does not exist</td>
    <td style="border: solid 1px">
        <ol>
            <li>Start the application</li>
            <li>Enter an invalid username</li>
            <li>Click the “LOGIN” button</li>
            <li>Confirm that an error message is displayed</li>
        </ol>
    </td>
    <td style="border: solid 1px">An error message is displayed</td>
    <td style="border: solid 1px">An error message is displayed</td>
    <td style="border: solid 1px">Pass</td>
    <td style="border: solid 1px"></td>
  </tr>
  <tr>
    <td style="border: solid 1px">10</td>
    <td style="border: solid 1px">Login</td>
    <td style="border: solid 1px">Failure to login - username string too long</td>
    <td style="border: solid 1px">
        <ol>
            <li>Start the application</li>
            <li>Enter an invalid username which is too long</li>
            <li>Click the “LOGIN” button and confirm that a field error message is displayed</li>
        </ol>
    </td>
    <td style="border: solid 1px">An error message is displayed</td>
    <td style="border: solid 1px">An error message is displayed</td>
    <td style="border: solid 1px">Pass</td>
    <td style="border: solid 1px"></td>
  </tr>
  <tr>
    <td style="border: solid 1px">11</td>
    <td style="border: solid 1px">Login</td>
    <td style="border: solid 1px">Failure to login - username contains non-printable characters</td>
    <td style="border: solid 1px">
        <ol>
            <li>Start the application</li>
            <li>Enter an invalid username which contains non-printable characters</li>
            <li>Click the “LOGIN” button and confirm that a field error message is displayed</li>
        </ol>
    </td>
    <td style="border: solid 1px">An error message is displayed</td>
    <td style="border: solid 1px">An error message is displayed</td>
    <td style="border: solid 1px">Pass</td>
    <td style="border: solid 1px"></td>
  </tr>
  <tr>
    <td style="border: solid 1px">12</td>
    <td style="border: solid 1px">Add Quiz</td>
    <td style="border: solid 1px">Successful addition of a quiz</td>
    <td style="border: solid 1px">
        <ol>
            <li>Start the application and successfully login.</li>
            <li>Click the “CREATE QUIZ” button and confirm that the "CREATE QUIZ" page is presented</li>
            <li>Enter valid form data</li>
            <li>Click the “CREATE” button</li>
            <li>Confirm that a new quiz is added and is made available to other users to be practiced</li>
        </ol>
    </td>
    <td style="border: solid 1px">A quiz is added and can be practiced by another user</td>
    <td style="border: solid 1px">A quiz is added and can be practiced by another user</td>
    <td style="border: solid 1px">Pass</td>
    <td style="border: solid 1px"></td>
  </tr>
  <tr>
    <td style="border: solid 1px">13</td>
    <td style="border: solid 1px">Add Quiz</td>
    <td style="border: solid 1px">Failure - Quiz name is not unique</td>
    <td style="border: solid 1px">
        <ol>
            <li>Start the application and successfully login.</li>
            <li>Click the “CREATE QUIZ” button</li>
            <li>Enter valid form data except set the quiz name to a value of another quiz already available in the database</li>
            <li>Click the “CREATE” button</li>
            <li>Confirm that an error message is displayed and the quiz details are not accepted in the database</li>
        </ol>
    </td>
    <td style="border: solid 1px">An error message is displayed</td>
    <td style="border: solid 1px">An error message is displayed</td>
    <td style="border: solid 1px">Pass</td>
    <td style="border: solid 1px"></td>
  </tr>
  <tr>
    <td style="border: solid 1px">14</td>
    <td style="border: solid 1px">Add Quiz</td>
    <td style="border: solid 1px">Failure - Quiz name is null or empty</td>
    <td style="border: solid 1px">
        <ol>
            <li>Start the application and successfully login.</li>
            <li>Click the “CREATE QUIZ” button</li>
            <li>Enter valid form data except omit the quiz name</li>
            <li>Click the “CREATE” button</li>
            <li>Confirm that a field error message is displayed</li>
        </ol>
    </td>
    <td style="border: solid 1px">An error message is displayed</td>
    <td style="border: solid 1px">An error message is displayed</td>
    <td style="border: solid 1px">Pass</td>
    <td style="border: solid 1px"></td>
  </tr>
  <tr>
    <td style="border: solid 1px">15</td>
    <td style="border: solid 1px">Add Quiz</td>
    <td style="border: solid 1px">Failure - Quiz name contains non-printable characters</td>
    <td style="border: solid 1px">
        <ol>
            <li>Start the application and successfully login.</li>
            <li>Click the “CREATE QUIZ” button</li>
            <li>Enter valid form data except for a quiz name</li>
            <li>Confirm user cannot enter non-printable characters</li>
        </ol>
    </td>
    <td style="border: solid 1px">User not allowed to enter non-printable characters</td>
    <td style="border: solid 1px">User not allowed to enter non-printable characters</td>
    <td style="border: solid 1px">Pass</td>
    <td style="border: solid 1px"></td>
  </tr>
  <tr>
    <td style="border: solid 1px">16</td>
    <td style="border: solid 1px">Add Quiz</td>
    <td style="border: solid 1px">Failure - No words defined</td>
    <td style="border: solid 1px">
        <ol>
            <li>Start the application and successfully login.</li>
            <li>Click the “CREATE QUIZ” button</li>
            <li>Enter valid form data for the quiz except do not include any words</li>
            <li>Confirm that the “CREATE” button is unavailable</li>
        </ol>
    </td>
    <td style="border: solid 1px">An error message is displayed</td>
    <td style="border: solid 1px">An error message is displayed</td>
    <td style="border: solid 1px">Pass</td>
    <td style="border: solid 1px"></td>
  </tr>
  <tr>
    <td style="border: solid 1px">17</td>
    <td style="border: solid 1px">Add Quiz</td>
    <td style="border: solid 1px">UI: user prevented from defining more than 10 words</td>
    <td style="border: solid 1px">
        <ol>
            <li>Start the application and successfully login.</li>
            <li>Click the “CREATE QUIZ” button</li>
            <li>Enter valid form data and 10 valid words with correct definitions</li>
            <li>Confirm that the “ADD WORD” button is unavailable once 10 words are entered</li>
        </ol>
    </td>
    <td style="border: solid 1px">An error message is displayed</td>
    <td style="border: solid 1px">An error message is displayed</td>
    <td style="border: solid 1px">Pass</td>
    <td style="border: solid 1px"></td>
  </tr>
  <tr>
    <td style="border: solid 1px">18</td>
    <td style="border: solid 1px">Add Quiz</td>
    <td style="border: solid 1px">Failure - word with null or empty correct definition</td>
    <td style="border: solid 1px">
        <ol>
            <li>Start the application and successfully login.</li>
            <li>Click the “CREATE QUIZ” button</li>
            <li>Enter valid form data and some valid words and leave at least one of the correct definition fields blank</li>
            <li>Click the “CREATE” button</li>
            <li>Confirm that a field error message displayed</li>
        </ol>
    </td>
    <td style="border: solid 1px">An error message is displayed</td>
    <td style="border: solid 1px">An error message is displayed</td>
    <td style="border: solid 1px">Pass</td>
    <td style="border: solid 1px"></td>
  </tr>
  <tr>
    <td style="border: solid 1px">19</td>
    <td style="border: solid 1px">Add Quiz</td>
    <td style="border: solid 1px">Failure - word correct definition with non-printable characters</td>
    <td style="border: solid 1px">
        <ol>
            <li>Start the application and successfully login.</li>
            <li>Click the “CREATE QUIZ” button</li>
            <li>Enter valid form data and some valid words and enter non-printable characters into at least one of the correct definition fields</li>
            <li>Click the “CREATE” button</li>
            <li>Confirm that user is not allowed to enter non-printable characters</li>
        </ol>
    </td>
    <td style="border: solid 1px">User not allowed to enter non-printable characters</td>
    <td style="border: solid 1px">User not allowed to enter non-printable characters</td>
    <td style="border: solid 1px">Pass</td>
    <td style="border: solid 1px"></td>
  </tr>
  <tr>
    <td style="border: solid 1px">20</td>
    <td style="border: solid 1px">Add Quiz</td>
    <td style="border: solid 1px">UI: enforce that the number of incorrect definitions is exactly 3x the number of correct definitions provided</td>
    <td style="border: solid 1px">
        <ol>
            <li>Start the application and successfully login.</li>
            <li>Click the “CREATE QUIZ” button</li>
            <li>Enter valid form data and some valid words</li>
            <li>Confirm that exactly 3 incorrect definition fields are displayed</li>
        </ol>
    </td>
    <td style="border: solid 1px">Exactly 3x the number of correct definition field are displayed</td>
    <td style="border: solid 1px">Exactly 3x the number of correct definition field are displayed</td>
    <td style="border: solid 1px">Pass</td>
    <td style="border: solid 1px"></td>
  </tr>
  <tr>
    <td style="border: solid 1px">21</td>
    <td style="border: solid 1px">Add Quiz</td>
    <td style="border: solid 1px">Failure - null or empty incorrect definition</td>
    <td style="border: solid 1px">
        <ol>
            <li>Start the application and successfully login.</li>
            <li>Click the “CREATE Quiz” button</li>
            <li>Enter valid form data and some valid words and leave at least one of the displayed incorrect definition fields blank</li>
            <li>Click the “CREATE” button</li>
            <li>Confirm that a field error message is displayed</li>
        </ol>
    </td>
    <td style="border: solid 1px">An error message is displayed</td>
    <td style="border: solid 1px">An error message is displayed</td>
    <td style="border: solid 1px">Pass</td>
    <td style="border: solid 1px"></td>
  </tr>
  <tr>
    <td style="border: solid 1px">22</td>
    <td style="border: solid 1px">Add Quiz</td>
    <td style="border: solid 1px">Failure - incorrect definition with non-printable characters</td>
    <td style="border: solid 1px">
        <ol>
            <li>Start the application and successfully login.</li>
            <li>Click the “CREATE Quiz” button</li>
            <li>Enter valid form data and some valid words and enter non-printable characters into at least one of the incorrect definition fields</li>
            <li>Click the “CREATE” button</li>
        </ol>
    </td>
    <td style="border: solid 1px">User not allowed to type non-printable characters</td>
    <td style="border: solid 1px">User not allowed to type non-printable characters</td>
    <td style="border: solid 1px">Pass</td>
    <td style="border: solid 1px"></td>
  </tr>
  <tr>
    <td style="border: solid 1px">23</td>
    <td style="border: solid 1px">Remove Quiz</td>
    <td style="border: solid 1px">Successful removal of a quiz - related scores are removed and related statistics are no longer available</td>
    <td style="border: solid 1px">
        <ol>
            <li>Start the application and successfully login.</li>
            <li>Select a quiz previously created by the currently logged in user and select the “REMOVE QUIZ” button</li>
            <li>Confirm that the quiz is successfully removed along with all related scores, resulting in statistics no longer being displayed</li>
        </ol>
    </td>
    <td style="border: solid 1px">The quiz is no longer available to practice and scores for all users relating to that quiz are no longer available, resulting in no statistics for that quiz</td>
    <td style="border: solid 1px">The quiz is no longer available to practice and scores for all users relating to that quiz are no longer available, resulting in no statistics for that quiz</td>
    <td style="border: solid 1px">Pass</td>
    <td style="border: solid 1px"></td>
  </tr>
  <tr>
    <td style="border: solid 1px">24</td>
    <td style="border: solid 1px">Remove Quiz</td>
    <td style="border: solid 1px">Failure - attempt to remove a quiz not created by the logged-in user</td>
    <td style="border: solid 1px">
        <ol>
            <li>Start the application and successfully login.</li>
            <li>Confirm that the “REMOVE QUIZ” button is not displayed for any quizzes which the currently logged in user did not create</li>
            <li>Using a unit test, confirm that a removal attempt directly against the database fails with an exception and the quiz along with all of its scores are not removed</li>
        </ol>
    </td>
    <td style="border: solid 1px">The "REMOVE QUIZ" button is not displayed for any quizzes which the currently logged in user did not create</td>
    <td style="border: solid 1px">The "REMOVE QUIZ" button is not displayed for any quizzes which the currently logged in user did not create</td>
    <td style="border: solid 1px">Pass</td>
    <td style="border: solid 1px"></td>
  </tr>
  <tr>
    <td style="border: solid 1px">25</td>
    <td style="border: solid 1px">Practice Quiz</td>
    <td style="border: solid 1px">Successfully initiate the practice of a quiz created by another user</td>
    <td style="border: solid 1px">
        <ol>
            <li>Start the application and successfully login.</li>
            <li>Select a quiz created by a user other than the currently logged in user and click the associated “PRACTICE QUIZ” button</li>
            <li>Confirm that the appropriate “PRACTICE QUIZ” page is displayed for the selected quiz</li>
        </ol>
    </td>
    <td style="border: solid 1px">Quiz practice session begins successfully</td>
    <td style="border: solid 1px">Quiz practice session begins successfully</td>
    <td style="border: solid 1px">Pass</td>
    <td style="border: solid 1px"></td>
  </tr>
  <tr>
    <td style="border: solid 1px">26</td>
    <td style="border: solid 1px">Practice Quiz</td>
    <td style="border: solid 1px">Failure - attempt to practice a quiz created by the currently logged-in user</td>
    <td style="border: solid 1px">
        <ol>
            <li>Start the application and successfully login.</li>
            <li>Confirm that the “PRACTICE QUIZ” button is not displayed for any quiz which was created by the currently logged in user.</li>
        </ol>
    </td>
    <td style="border: solid 1px">The “PRACTICE QUIZ” button is not displayed for any quiz which was created by the currently logged in user</td>
    <td style="border: solid 1px">The “PRACTICE QUIZ” button is not displayed for any quiz which was created by the currently logged in user</td>
    <td style="border: solid 1px">Pass</td>
    <td style="border: solid 1px"></td>
  </tr>
  <tr>
    <td style="border: solid 1px">27</td>
    <td style="border: solid 1px">Practice Quiz</td>
    <td style="border: solid 1px">Successfully select a correct definition and receive affirmative message</td>
    <td style="border: solid 1px">
        <ol>
            <li>Start the application and successfully login.</li>
            <li>Select a quiz created by a user other than the currently logged in user and click the associated “PRACTICE QUIZ” button</li>
            <li>Select the correct definition for the first displayed word</li>
            <li>Confirm that there is a visual indication of correctly answering the question</li>
        </ol>
    </td>
    <td style="border: solid 1px">Message indicating that the user selected the correct definition appears before transitioning to the next word</td>
    <td style="border: solid 1px">Message indicating that the user selected the correct definition appears after transitioning to the next word</td>
    <td style="border: solid 1px">Pass</td>
    <td style="border: solid 1px"></td>
  </tr>
  <tr>
    <td style="border: solid 1px">28</td>
    <td style="border: solid 1px">Practice Quiz</td>
    <td style="border: solid 1px">Successfully select an incorrect definition and receive an appropriate message</td>
    <td style="border: solid 1px">
        <ol>
            <li>Start the application and successfully login.</li>
            <li>Select a quiz created by a user other than the currently logged in user and click the associated “PRACTICE QUIZ” button</li>
            <li>Select the correct definition for the first displayed word</li>
            <li>Confirm that there is a visual indication of incorrectly answering the question</li>
        </ol>
    </td>
    <td style="border: solid 1px">Message indicating that the user selected an incorrect definition appears while transitioning to the next word</td>
    <td style="border: solid 1px">Message indicating that the user selected an incorrect definition appears while transitioning to the next word</td>
    <td style="border: solid 1px">Pass</td>
    <td style="border: solid 1px"></td>
  </tr>
  <tr>
    <td style="border: solid 1px">29</td>
    <td style="border: solid 1px">Practice Quiz</td>
    <td style="border: solid 1px">Successfully submit a completed quiz</td>
    <td style="border: solid 1px">
        <ol>
            <li>Start the application and successfully login.</li>
            <li>Select a quiz created by a user other than the currently logged in user and click the associated “PRACTICE QUIZ” button</li>
            <li>Respond to all quiz questions</li>
            <li>Confirm that the quiz is submitted and that a score is recorded</li>
        </ol>
    </td>
    <td style="border: solid 1px">The quiz score is submitted when all quiz session questions have been responded to (and at no other time)</td>
    <td style="border: solid 1px">The quiz score is submitted when all quiz session question have been responded to (and at no other time)</td>
    <td style="border: solid 1px">Pass</td>
    <td style="border: solid 1px"></td>
  </tr>
  <tr>
    <td style="border: solid 1px">30</td>
    <td style="border: solid 1px">Practice Quiz</td>
    <td style="border: solid 1px">Successfully terminate the program while practicing a quiz - confirm score is not saved and no related statistics are affected</td>
    <td style="border: solid 1px">
        <ol>
            <li>Start the application and successfully login.</li>
            <li>Select a quiz created by a user other than the currently logged in user and click the associated “PRACTICE QUIZ” button</li>
            <li>Prior to answering all quiz questions, close the application.</li>
            <li>Restart the application and successfully login</li>
            <li>Confirm that the score was not submitted to the database and that the statistics for the non-completed quiz were not affected.</li>
        </ol>
    </td>
    <td style="border: solid 1px">Program terminates and upon restart no scores have been entered in the database</td>
    <td style="border: solid 1px">Program terminates and upon restart no scores have been entered in the database</td>
    <td style="border: solid 1px">Pass</td>
    <td style="border: solid 1px"></td>
  </tr>
  <tr>
    <td style="border: solid 1px">31</td>
    <td style="border: solid 1px">View Statistics</td>
    <td style="border: solid 1px">Successfully view quiz statistics for the currently logged in user</td>
    <td style="border: solid 1px">
        <ol>
            <li>Start the application and successfully login.</li>
            <li>Confirm that quiz statistics are available for all quizzes that the currently logged in user has completed. Also that up to 3 100% scorer usernames are displayed even for quizzes which the currently logged in user has not practiced</li>
        </ol>
    </td>
    <td style="border: solid 1px">User can view quiz statistics for all quizzes he has taken, as well as the top 3 students who have scored 100% for each quiz</td>
    <td style="border: solid 1px">User can view quiz statistics for all quizzes he has taken, as well as the top 3 students who have scored 100% for each quiz</td>
    <td style="border: solid 1px">Pass</td>
    <td style="border: solid 1px"></td>
  </tr>
  <tr>
    <td style="border: solid 1px">32</td>
    <td style="border: solid 1px">View Statistics</td>
    <td style="border: solid 1px">Failure - attempt to view quiz statistics for another user (other than first 3 100%)</td>
    <td style="border: solid 1px">
        <ol>
            <li>Start the application and successfully login.</li>
            <li>Confirm that personal user statistics are not revealed for users other than the currently logged-in user.</li>
        </ol>
    </td>
    <td style="border: solid 1px">User not allowed to view others users scores other than the list of first 3 students scoring 100%</td>
    <td style="border: solid 1px">User not allowed to view others users scores other than the list of first 3 students scoring 100%</td>
    <td style="border: solid 1px">Pass</td>
    <td style="border: solid 1px"></td>
  </tr>
  <tr>
    <td style="border: solid 1px">33</td>
    <td style="border: solid 1px">Logout from Quiz App</td>
    <td style="border: solid 1px">User can successfully Logout</td>
    <td style="border: solid 1px">
        <ol>
            <li>Start the application and successfully login.</li>
            <li>Click the Logout button</li>
            <li>Verify that user is redirected to the Login screen</li>
        </ol>
    </td>
    <td style="border: solid 1px">User is redirected to the Login screen</td>
    <td style="border: solid 1px">User is redirected to the Login screen</td>
    <td style="border: solid 1px">Pass</td>
    <td style="border: solid 1px"></td>
  </tr>
  <tr>
    <td style="border: solid 1px">34</td>
    <td style="border: solid 1px">Cancel the current operation</td>
    <td style="border: solid 1px">User can navigate back to Main screen</td>
    <td style="border: solid 1px">
        <ol>
            <li>Start the application and successfully login.</li>
            <li>Click the Create Quiz button</li>
            <li>Click the CANCEL button</li>
            <li>Verify that user is redirected to the Main screen</li>
        </ol>
    </td>
    <td style="border: solid 1px">User is redirected to the Main screen</td>
    <td style="border: solid 1px">User is redirected to the Main screen</td>
    <td style="border: solid 1px">Pass</td>
    <td style="border: solid 1px"></td>
  </tr>
</table>
