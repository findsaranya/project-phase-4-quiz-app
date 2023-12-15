import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { QuizService } from './quiz';

const routes: Routes = [
  {
    path:"",
    providers:[QuizService],
    loadComponent : () => import("./quiz/quiz.component")
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
