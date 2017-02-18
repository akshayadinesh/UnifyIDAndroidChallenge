# UnifyIDAndroidChallenge

# Build Instructions

Go to Android Studio and create a new project. Then, copy and paste the XML code from `activity_main.xml` and copy and paste the Java code from `ActivityMain.java`. If there are errors, look at the other files and make sure everything matches. The, run the app like usual.

# Further Considerations

In order to make the snapshots secure, they are stored internally in an app-specific storage folder. Android makes this possible with the `getExternalFilesDir()` command, which saves data privately. However, this can definitely be made more secure by encoding the files with a private key so attackers cannot access the pictures.

Also, the video initially taken is deleted from the internal storage so attackers cannot process it themselves and reproduce the pictures.

In the future, I would like to take all 10 pictures automatically instead of taking a video and processing it. This makes it easier on the user to take the pictures.
