# Extra Requirements

### Version History

<table class="tg" style="border: solid 1px;border-collapse:collapse;">
  <tr>
    <th style="border: solid 1px">Version</th>
    <th style="border: solid 1px">Description</th>
    <th style="border: solid 1px">Details</th>
  </tr>  <tr>       <td style="border: solid 1px">1.0</td>      <td style="border: solid 1px">Extra requirements for the Quiz App</td>      <td style="border: solid 1px">Initial Supplementary requirement document</td>  </tr>  <tr>
       <td style="border: solid 1px">2.0</td>
      <td style="border: solid 1px">Final Supplementary requirements document</td>
      <td style="border: solid 1px">         <ul>             <li>Added few more supplementary requirements</li><li>Added version history</li></ul></td>
  </tr></table>

**Author**: Team 10
* Bharati Singh, bsingh60
* Hao Luo, hluo33
* Nathan Turnbow, nturnbow3
* Rajan Jethva, rjethva3


### Supplementary requirements:

- This app will support only English words and will not support any other languages
- One user will be able to create multiple usernames.
- Users will never be deleted from the system.
- User will easily impersonate another user if s/he knows other user’s username
- All correct definitions and options must be unique in one quiz. Same word cannot be repeated for correct definition and option.
- While creating quiz there should not be any word text box empty
- Every Quiz can be attempted as many times as desired by any student.
- After every attempt on a Quiz is completed, its individual score will be stored in the underlying database.
- Student’s partial attempt score will not be persisted and will not be displayed in stats. Student has to start the quiz from start when s/he choose to practice again
- Once removed quiz and it’s stats will not be rolled back by any means.
- User will not see other user’s stats except top 3 student details who got 100 for the given quiz
- There can only be one instance of the app running on a particular device at any time.
- The app should have an underlying database to save persistent information across sessions (e.g., No. of attempts, Quiz scores etc).
- There is a Logout option on the Home Page of the app using which student can log out of the quiz and another student can login to the same app.
- User will also logged out of quiz app when student will close the app
- User session will not be maintained while restarting the quiz
- The User Interface (UI) is intuitive and responsive.
- The system should provide for ease of flexibility (e.g., additional feature functionality).
- The system should provide for ease of maintainability (e.g., coding standards & bug-fix process).
- The system should provide for ease of implementation. (e.g., configuration management process:  source code management, build, deploy, etc.).
