import { Component, OnInit } from '@angular/core';
import { CourseService } from '../../services/course.service'; 
import { UserService } from '../../services/user.service'; 
import { Transaction } from '../../models/transaction';
import { Router } from '@angular/router';
import { DatePipe } from '@angular/common';
import { User } from '../../models/user';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  currentUser: User;
  transactionList: Array<User>;

  constructor(private userService: UserService, private courseService: CourseService, private router: Router) {
    this.currentUser = this.userService.currentUserValue;
  }

  ngOnInit(): void {
    if(!this.currentUser){
      this.router.navigate(['/home']);
      return;
    }
  }

  findTransactionOfUser(){
    this.courseService.findTransactionsOfUser(this.currentUser.id).subscribe(data => {
      this.transactionList = data;
    });
  }

  logout(){
    this.userService.logout().subscribe(data => {
      this.router.navigate(['/login']);
    });
  }
}
