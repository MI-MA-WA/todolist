package de.thkoeln.gm.todolistgdwdemo.users

import jakarta.validation.constraints.Email

 class UserForm  {
    @Email var email: String = ""
}