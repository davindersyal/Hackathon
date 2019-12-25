
# Device Management
This app is used to manage the device by removing the manual  system to manage assigned  and unassigned device (eg. To resolve the check-in and check-out mobile device system in Our ISTeam or any other company )

## Download APK from below link
Diawi link
https://i.diawi.com/DZYQyk

Or

Dropbox link
https://www.dropbox.com/s/pw9iptpdfea1jpk/device_management.apk?dl=0

### How to test 

Install APK in two device to test functionalitity properly one will act as admin and other will act as device to be allocated . 

1. Register device : 
   - Enter valid code e.g SDM001(use any code from 001 to 010 that are regsitered on server) and Click on verify 
   - It will show you QR code to be scan when you want to assign device to the developer .
   - Logout :- it will delete device and unassigned the verification code 


  2. Admin Login 
  2.1  Allocate device to developer 
        - Click on device name that you want to assign the developer (which is present in Not assigned device list)
       - Scan QR code and fill developer name and seat code and press assign
   2.2 Deallocate the device  
      - Click on device name that developer want to submit to ISTEAM (which is present in assigned device list)
     - It will show whole detail of device , if its validate click on Unassigned . That device can be submitted to ISTEAM.


## Purpose:
This app is used to manage the device by automate the manual process of assigning device to developer and check the detail which device is assigned to which developer (eg. To resolve the check-in and check-out mobile device system in Our ISTeam or any other company)


## Module :
1. Register device 
2. Admin login

### Register Device 
1. ISTeam will save the device code manually in admin panel so that unauthorised device can’t be save . These code will normal device code like SDM_Mobile_12,  SDM_Mobile_13, SDM_Mobile_14 etc 
2. Click on register device -> Enter verification Code -> If device is  verified then you will get respective message
3. After successfully verifying code , device detail will be saved in background on server and you will get the QR code for scanning .

### Admin login
1. Verify admin code: -  for time being we saved some code on server that only be used by admin to check the list and access the feature 
2. After successfully verifying code, Device list will be show (Home Page)
3. Device list have 2 section
      3.1 Not Assigned :- It will show list of all registered device that are not allocated to anyone 
      3.2 Assigned :- It will show list of devices that are allocated to developer 
  4. Click on any Not Assigned item 

4.1 It will open a scanner to scan the QR code (that was generated in Register device module) and to verify the QR code.
4.2 If code is Verified , it will open a screen where developer have to enter the name and seat number (as we did in register)
4.3 Then it will save that developer detail on server and refresh that list of assigned device list

5. Click on assigned item 
5.1  It will show the pre-filled detail of device and user to wwhom it was assigned . You can click “un assigned” to deallocated the device. 
5.2 Once the device is successfully unassigned it will again shown in the NOT ASSIGNED list  as its free device so any one can take it for testing.  
