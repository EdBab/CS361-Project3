UTEID: eab2982; dmd2479; dic223; FIRSTNAME: Eddie; Daniel; Dylan; LASTNAME: Babbe; Duncan; Inglis; CSACCOUNT: dmduncan; babbe99; cinglis EMAIL: daniel.duncan@utexas.edu; babbe2012@utexas.edu; dylanmcquade@yahoo.com

Note: Professor Young has given us permission to work as a team of 3. You can confirm with him if needed.

[Program 3] [Description]
In the beginning, our code reads in the flags off of the terminal and determines whether we are encrypting or decrypting, as well as which files we should be reading and/or writing too.
From there, the paths diverge. If encrypting, we print the statistics on the image we are reading in. We then construct a while loop that reads the message in line by line, and after a few 
short lines of code we manage to find the binary string representation of that entire line of text. The line is then sent to imbedMess() which sends 3 bits at a time to transitionToNew() which modifies the
bits in order to pass the message. It also checks there to ensure we have enough room for the 0 bit. After returning, we also add a byte to ensure a line is skipped. When we are done, we add a 0 byte. For decryption, we read the image 
pixel by pixel, and create a string to represent the gathered bits. When there is 8, we print that character to the provided file. We do this until a byte is found in image's pixels. That is essentially the encryption and decryption process.


[Finish] We have completely finished the assignment.

[Questions]

1. Comparing your original and modified images carefully, can you detect *any* difference visually (that is, in the appearance of the image)? - No, we cannot see any difference visually in the photo and we shouldn't be able to if the Steg was implemented correctly.

2. Can you think of other ways you might hide the message in image files (or in other types of files)? - There is a fairly easy non-sophisticated way to do it just with a picture and notepad on windows. You save the image as a BMP and then write a message with notepad. Have them named the same, so say its called message.bmp and message.txt. Once you do that go into the command prompt and wite copy “<image file path>” + “<text file path>” “<new image path>”. The new image will now be a BMP file and can be opened just by double clicking. However, if someone knows there is a message in the image you can simply open it with notepad and the message will be written at the bottom.

3. Can you invent ways to increase the bandwidth of the channel? - Yes, you could make the last 2 bits say mod 4 instead of mod 2 which in turn would allow you to pass more data choices through (i.e 00,01,10,11 rather than just 0 or 1). You can keep doing this and make the channel bigger and bigger, the problem you will soon run into though is that the image will start to be distorted and be very obvious it has been tampered with.

4. Suppose you were tasked to build an "image firewall" that would block images containing hidden messages. Could you do it? How might you approach this problem? - Well if you had the original, unmodified image to have a comparison then this becomes fairly easy. You would simply compare it against the original image and it would show the differences caused by encoding the image which would show it has been tampered with and you could block it.

- If you don't have the original image to compare it to, then looking for patterns and common irregularities becomes some of the best approaches. A lot of the time it comes down to looking at noise profiles and especially the least significant bits to have different noise profiles than the rest because most common steganographic algorithms modify the least significant bits. Least significant bit insertion in a palette-based image often causes a large number of duplicate colors, where identical (or nearly identical) colors appear twice in the palette and differ in the least significant bit. So, our firewall could quickly search for the same color pixels and inspect to see if the bits are the same.

5. Does this fit our definition of a covert channel? Explain your answer. - A covert channel is generally defined as any manner of transferring data by means that were not intended for that purpose. They include all vehicles that would allow the direct or indirect writing of a storage location by one process and the direct or indirect reading of it by another. Based on all of the qualities that steganography uses we believe it fits that general definition of a covert channel. Steganography takes a normally harmless piece of data and hides secret data within it. The highway for storing the secret message was never intended to be used for that purpose and, the receiver of the message is aware of the writings to it and is able to read it. Thus, this resembles very closely to the structure of a covert channel.
