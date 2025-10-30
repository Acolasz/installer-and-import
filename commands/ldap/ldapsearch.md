# Usage

```shell
ldapsearch -x -H ldap://localhost:3890 \
-b "dc=emap,dc=hu" \
-D "cn=admin,dc=emap,dc=hu" \
-w 626fzJQThN9i38kH6l2o
ldapsearch -x -H ldap://localhost:3890 \
-b "ou=Groups,dc=emap,dc=hu" \
-D "cn=admin,dc=emap,dc=hu" \
-w 626fzJQThN9i38kH6l2o

ldapsearch -x -H ldap://localhost:3890 \
-D "cn=admin,dc=emap,dc=hu" \
-w 626fzJQThN9i38kH6l2o \
-b "ou=Groups,dc=emap,dc=hu" "(uid=testuser1)"

ldapsearch -x -LLL -H ldap://localhost:3890 \
-D "cn=admin,dc=emap,dc=hu" \
-w 626fzJQThN9i38kH6l2o \
-b "ou=Groups,dc=emap,dc=hu" "(memberUid=testuser1)"

ldapsearch -x -LLL -H ldap://localhost:3890 \
-D "cn=admin,dc=emap,dc=hu" \
-w 626fzJQThN9i38kH6l2o \
-b "ou=Groups,dc=emap,dc=hu" "(objectClass=posixGroup)"
```