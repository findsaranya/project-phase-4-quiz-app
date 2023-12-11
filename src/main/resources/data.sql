INSERT INTO quiz_tab(quiz_name) VALUES ('Php_Mysqli'),('Ip Networking');

INSERT INTO ques_tab(quizid,quesname) VALUES (1,'What does PHP stand for?'),
(1,'Who is the father of PHP?'),
(1,'PHP files have a default file extension of.'),
(1,'Which of the looping statements is/are supported by PHP?'),
(1,'Which of the following PHP statements will output Hello World on the screen?'),
(1,'Which one of the following function is capable of reading a file into an array?'),
(2,'How long is an IPv6 address?'),
(2,'Which protocol does DHCP use at the Transport layer?'),
(2,'Where is a hub specified in the OSI model?'),
(2,'Which of the following is private IP address?'),
(2,'If you use either Telnet or FTP, which is the highest layer you are using to transmit data?'),
(2,'Which of the following is a layer 2 protocol used to maintain a loop-free network?'),
(2,'What is the maximum number of IP addresses that can be assigned to hosts on a local subnet that uses the 255.255.255.224 subnet mask?'),
(2,'You need to subnet a network that has 5 subnets, each with at least 16 hosts. Which classful subnet mask would you use?'),
(2,'You have an interface on a router with the IP address of 192.168.192.10/29. Including the router interface, how many hosts can have IP addresses on the LAN attached to the router interface?'),
(2,'To test the IP stack on your local host, which IP address would you ping?')
;

INSERT INTO ques_option_tab(is_ans,ques_id,option_name) VALUES (false,1,'Personal Home Page'),
(false,1,'Private Home Page'),
(true,1,'Pretext Hypertext Processor'),
(false,1,'Preprocessor Home Page'),(true,2,'Rasmus Lerdorf'),
(false,2,'Willam Makepiece'),
(false,2,'Drek Kolkevi'),
(true,2,'List Barely');