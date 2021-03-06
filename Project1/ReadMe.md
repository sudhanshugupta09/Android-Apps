# Project Title
EASY CONTACT SAVE

## Overview
The app starts off with a main activity containing a welcome text and two buttons. Upon pressing the first button,
the app displays a second activity containing a read-only text field and an edit text field. The read-only
text field prompts the device user to enter a person’s name in the edit text field. A legal name consists at
least of a first name and a last name, each containing a sequence of alphabetical characters and separated
by one or more space characters. Leading and trailing white space characters should be ignored. When the
user is done entering the name, he/she will press the done or return key in the soft keyboard. The activity must
now check whether a legal name was entered by the user. In this case, the activity will set a result code
of “RESULT OK”; otherwise, the activity sets a result code of “RESULT CANCELED”. Either way, the
second activity terminates itself, thereby causing the first activity to become visible again.
Upon returning from the second activity, the first activity checks whether the result code was “RESULT
OK”. In the case, the user may press the second button in the first activity causing the device to
display the contacts activity, while displaying the name that was entered in the second activity in the “Edit
Contact” activity. You must use an existing Contacts app pre-installed in your device when displaying the
“edit contact” activity. However, if the result code was “RESULT CANCELED”, when the user presses the second button,
the first activity displays a toast message informing that device user that she entered an incorrect name
and includes the name in the toast.
