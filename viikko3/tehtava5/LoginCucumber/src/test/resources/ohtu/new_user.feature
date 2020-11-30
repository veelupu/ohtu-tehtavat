Feature: A new user account can be created if a proper unused username and password are given

    Scenario: creation is successful with valid username and password
        Given command new is selected
        When  username "antti" and password "rap4rp3r1" are entered
        Then  system will respond with "new user registered"
    
    Scenario: creation fails with already taken username and valid password
        Given command new is selected
        When  username "pekka" and password "sohv4tyyny" are entered
        Then  system will respond with "new user not registered"   


    Scenario: creation fails with too short username and valid password
        Given command new is selected
        When  username "de" and password "kynatelin3" are entered
        Then  system will respond with "new user not registered"

    Scenario: creation fails with valid username and too short password
        Given command new is selected
        When  username "helga" and password "keitti3" are entered
        Then  system will respond with "new user not registered"

    Scenario: creation fails with valid username and password long enough but consisting of only letters
        Given command new is selected
        When  username "helga" and password "kivikasa" are entered
        Then  system will respond with "new user not registered"

    Scenario: can login with successfully generated account
        Given user "eero" with password "salainen1" is created
        And   command login is selected
        When  username "eero" and password "salainen1" are entered
        Then  system will respond with "logged in"

 
