User Service API with basic Spring Security
===========================================
While creating the project we need to add the following dependency
1. Spring Web
2. Spring Security
3. Spring data JPA
4. mysql driver
5. lombok
6. Thymleaf

Note : Which depedency we need it's  depends on our project requirement if we want to add new feature then sometimes we need to add more depedency.

in this project we have following packages and classes
1. User Entity
2. User Service
3. MyUserDetailsService
4. UserRepo
4. UserController
5. viewController
6. SecurityConfig

For Front end we are using home.html / register.html / login.html

Note : If we don't need authenticate the user from DB then no need to create the MyUserDetailsService class.

⭐ When do you REALLY need a UserDetailsService?

You need it ONLY when users come from the database.
For example:

User registration module

JWT authentication

Login using email/mobile

Custom roles and permissions

---------------------------------
🚀 SPRING SECURITY + USER REGISTRATION + DB AUTHENTICATION — FULL FLOW

🧩 1. USER REGISTRATION FLOW
----------------------
1. When we hit the POST endpoint user sends the Json request (POST /api/v1/users/create)
2. Controller receives this body
            Because of @RequestBody User user, JSON binds to your User entity.

3. UserServiceImpl.registerUser() is called
✔ Password becomes BCrypt encrypted
✔ User saved in MySQL table TB_USER_INFO

🧩 2. User Login Flow (Default Spring Login Page)
-------------------------------------------------
Step 1 → User enters:

Username = email

Password = raw password

Step 2 → Spring Security receives credentials

It internally calls your UserDetailsService:

Step 3 → Load user from DB using repository:
✔ If email exists → User is returned
❌ If not → Authentication fails

Step 4 → Spring compares password using BCrypt
✔ If matches → Login success
❌ If not → Login fail ("Bad credentials")

🧩 3. SecurityConfig Controls Everything
-------------------------------------------
Your SecurityConfig defines:

1.Password encoder → BCrypt
2️.Authentication source → UserDetailsService
3️.Authorization → requires login
4️.Form login enabled
5️.CSRF settings


🧩 4. Summary — Full Architecture
---------------------------------
    ┌────────────────────┐
    │  Client / Browser  │
    └───────┬────────────┘
            │ JSON Register Request
            ▼
    ┌──────────────────────────────┐
    │  UserServiceController       │
    └───────┬──────────────────────┘
            │ calls registerUser()
            ▼
    ┌──────────────────────────────┐
    │  UserServiceImpl             │
    │  - encrypt password          │
    │  - save user to DB           │
    └───────┬──────────────────────┘
            ▼
    ┌──────────────────────────────┐
    │  MySQL (TB_USER_INFO)        │
    └──────────────────────────────┘


            ▲
            │ Login request
    ┌───────┴──────────────────────┐
    │ Spring Security Login Filter │
    └───────┬──────────────────────┘
            │ load by email
            ▼
    ┌──────────────────────────────┐
    │   UserDetailsService         │
    └───────┬──────────────────────┘
            │ findByEmail()
            ▼
    ┌──────────────────────────────┐
    │  UserRepo (JPA Repository)   │
    └───────┬──────────────────────┘
            │ returns user
            ▼
    ┌──────────────────────────────┐
    │ BCryptPasswordEncoder        │
    └──────────────────────────────┘

