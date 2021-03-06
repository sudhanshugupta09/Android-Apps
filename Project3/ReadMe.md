# Project Title
BROADCASTS AND SECURITY

## Overview
Here is a short summary of the apps:

1. Application A1 defines a new dangerous-level permission called “edu.uic.cs478.f18.project3” and sends
one of two kinds of broadcasts. Both broadcasts use implicit intents. The other two applications will
receive the broadcasts; however, these applications will receive the broadcasts only if the sender (i.e.,
A1) has that permission. App A1 specifically defines an activity containing two read-only text views
and two buttons. The buttons, when selected, will broadcast two different intents with actions concerning
points of interest in the cities of San Francisco, CA and New York, NY, depending on the
button pressed. The text views describe the meaning of the buttons to the device user. Both broadcasts
are ordered broadcasts. Moreover, both broadcasts require that a receiver have acquired permission
“edu.uic.cs478.f18.project3” in order to respond to the broadcast. App A1 must also acquire this permission
before sending the broadcasts.

2. Application A2 receives the intents sent by A1; A2 contains a single activity that defines a welcome
message and a button. When the button is pressed, the activity checks whether the permission has
been acquired “edu.uic.cs478.f18.project3”. If not, it requests the permission. App A2 also define two
broadcast receivers programmatically, one for each of the two broadcasts by A1. Whenever a broadcast
intent is received, A2 displays a toast message on the device’s display. The toast message indicates
whether the broadcast sender was selecting San Francisco or New York. However, A2’s broadcast
receiver is designed in such a way that it will only respond to a broadcast if the broadcast sender has
permission “edu.uic.cs478.f18.project3”.

3. Application A3 also receives A1’s broadcasts if the sender has permission “edu.uic.cs478.f18.project3”.
Depending on the intent received, A3 will launch one of two activities. The first activity displays
information about at least 6 points of interest in San Francisco. The second activity should show points
of interest in New York; however, you are not responsible for coding this activity. Just display a toast
message indicating the New York information is under construction. A3 defines also a single broadcast
receiver statically; this receiver must respond to both kinds of intents sent by A1.
The San Francisco activity consists of two fragments, whose behavior is described below. Finally,
application A3 maintains an action bar. The action bar shows the name of the application and an icon
associated with the application (your choice).

The San Francisco activity in A3 contains two fragments. The first fragment displays a list of points of interest
for the city. The device user may select any point from the list; the currently selected item is highlighted. The
second fragment shows the official web page of the selected item.

When the device is in portrait mode, the two fragments are displayed on different screens. First, the
device will show only the first fragment. When the user selects an item, the the first fragment disappears and
the second fragment is shown. Pressing the “back” soft button on the device, will return the device to the
original configuration (first fragment only), thereby allowing the user to select a different point of interest.
When the device is in landscape mode, application A3 initially shows only the first fragment across the entire
width of the screen. As soon as a user selects an item, the first fragment is “shrunk” to about 1/3 of the screen’s
width. This fragment will appear in the left-hand side of the screen, with the second fragment taking up the
remaining 2/3 of the display on the right. Again, pressing the “back” button will return the application to its
initial configuration. The action bar should be displayed at all times regardless of whether the device is in
portrait or landscape mode.

Finally, the state of application A3 should be retained across device rotations, e.g., when the device is
switched from landscape to portrait configuration and vice versa. This means that the selected list item (in the
first fragment) and the page displayed in the second fragment will be kept during configuration changes.
As for the order of execution of A2 and A3’s receivers, you should configure these apps in such a way that
a receiver in A2 is always executed before the receiver in A3, after A1 sends a broadcast.
