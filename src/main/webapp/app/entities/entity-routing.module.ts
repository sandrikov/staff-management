import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'department',
        data: { pageTitle: 'Departments' },
        loadChildren: () => import('./department/department.routes'),
      },
      {
        path: 'job',
        data: { pageTitle: 'Jobs' },
        loadChildren: () => import('./job/job.routes'),
      },
      {
        path: 'employee',
        data: { pageTitle: 'Employees' },
        loadChildren: () => import('./employee/employee.routes'),
      },
      {
        path: 'job-history',
        data: { pageTitle: 'JobHistories' },
        loadChildren: () => import('./job-history/job-history.routes'),
      },
      {
        path: 'role',
        data: { pageTitle: 'Roles' },
        loadChildren: () => import('./role/role.routes'),
      },
      {
        path: 'project',
        data: { pageTitle: 'Projects' },
        loadChildren: () => import('./project/project.routes'),
      },
      {
        path: 'project-member',
        data: { pageTitle: 'ProjectMembers' },
        loadChildren: () => import('./project-member/project-member.routes'),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class EntityRoutingModule {}
