# UserManagement


Instructions: Write a user management class that can add a user, authenticate, list users.
(Don't worry about persistence)

normally, I would ask far more questions regarding requirements before coding anything,
but as this is a 'see what you come up with' scenario, here goes.

Approach:
What I tried to do is store the User object in a HashMap where the key is the username, since usernames should not be duplicated. The HashMap stores users once added to the system.  The list method just returns a list of the keys to the hashmap, which is essentially a list of the usernames. 

Also, for storing the password, I don't want to do this and be responsible for securing and storing them in plain text, so I will SHA-256 hash them with a salt, which should not be reversible. I never want to store or log the user provided password for any reason. Comparison to see if the password is correct can be done by comparing the salted hash. 
