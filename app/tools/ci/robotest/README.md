The sanity.json file holds Robotest instructions for sanity testing.
Sanity Testing, means "just a quick check, to ensure we haven't got any runtime crashes."
The file holds commands, to tap 3 items on the screen

Note that if a RoboTest has a longer timeout than these 3 instructions take, TestLab will use the 
rest of the time to click around and 'sanity test' whatever it feels like clicking.