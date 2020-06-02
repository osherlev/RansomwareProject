# Ransomware Project

Note: This project is only for educational purpose, use at your own risk. I do not encourage in any way the use of this software illegally or to attack targets without their previous authorization.	

## What is Ransomware?
Ransomware is a type of malware that prevents or limits users from accessing their system, either by locking the system's screen or by locking the users' files unless a ransom is paid. More modern ransomware families, collectively categorized as crypto-ransomware, encrypt certain file types on infected systems and forces users to pay the ransom through certain online payment methods to get a decrypt key.

## Project summery
This project aims to build an almost functional crypto-ransomware for educational purposes, written in pure java. Basically, it will encrypt your files in background using a random symmetric encryption algorithm from the following - AES,DESede,Blowish or Twofish.

The project is composed by two parts, the server and the malware

The server is choosing a random algorithm and creates symetric key according to it, then he stores the victim's identification key(his ip) along with the encryption key used by the malware and the chosen algorithm in mySQL database.

## Features
* Use of Spring,lombok and apache annotations
* Generic
* Using configuretion files
* Encrypts the whole file system using BFS algorithm
* Run in Background
* Encrypt files using AES,DESede,Blowfish or Twofish algorithms which are chosen randomly.
* Multithreaded.
* Includes decrypter.
* Use Cypher with stream encryption to avoid load an entire file into memory.
* Walk all drives by default.

# Important
DON'T RUN RansomwareProject.jar IN YOUR PERSONAL MACHINE, EXECUTE ONLY IN A TEST ENVIRONMENT(VMWARE)! I'm not resposible if you acidentally encrypt all of your disks!

# Usage and How it Works
The easiest way to run this Project is to use the the .jar --> open two cmd terminals and simply run the below commands.
in the first cmd terminal - 
```
$ cd CNCJarPath
```
Starting server
```
$ java -jar CNCserverPro.jar
```
 in the second cmd terminal run the following commands:
```
$ cd RansomJarPath
```
Encrypt All Files in the Current Path wait until the execution will be finished
```
$ java -jar RansomwareProject.jar 
```


