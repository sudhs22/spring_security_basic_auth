The bootstrap user and the role needs to be created in MySQL DB 
using below MySQL scripts.

# Bootstrap Roles
insert into `role` (`id`, `name`) values('2','ROLE_UPDATE');
insert into `role` (`id`, `name`) values('1','ROLE_VIEW');

# Bootstrap Users with a password as "password"
insert into `user` (`id`, `email`, `password`, `username`) values('1','a@a.com','$2a$10$zDc/D2DdbBBJ1brN9HNWXOHG2hhQLG6V2MQmtiIR84tpAFopLpJZ6','user');
insert into `user` (`id`, `email`, `password`, `username`) values('2','b@b.com','$2a$10$zDc/D2DdbBBJ1brN9HNWXOHG2hhQLG6V2MQmtiIR84tpAFopLpJZ6','admin');


# Bootstrap Assign Roles to Users
insert into `user_role` (`user_id`, `role_id`) values('1','1');
insert into `user_role` (`user_id`, `role_id`) values('2','2');






 Access the below URLs to test the application
1. http://localhost:8080/test/role/view with user "user" and password "password" in postman with basic auth
2. http://localhost:8080/test/role/update with user "user" and password "password" in postman with basic auth
   This will fail with 403
3. http://localhost:8080/test/role/update with user "admin" and password "password" in postman with basic auth   
 