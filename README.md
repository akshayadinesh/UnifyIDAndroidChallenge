# UnifyIDAndroidChallenge

# Build Instructions

You can build the project by dowloading the folder and Importing it into Android Studio. Connect an Android device or emulator as usual and Click Play. It should work properly.

# Further Considerations

In order to make the snapshots secure, they are stored internally in an app-specific storage folder. Android makes this possible with the `getExternalFilesDir()` command, which saves data privately. However, this can definitely be made more secure by encoding the files with a private key so attackers cannot access the pictures.

Also, the video initially taken is deleted from the internal storage so attackers cannot process it themselves and reproduce the pictures.

In the future, I would like to take all 10 pictures automatically instead of taking a video and processing it. This makes it easier on the user to take the pictures.
