import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import { ProjectMemberComponent } from './list/project-member.component';
import { ProjectMemberDetailComponent } from './detail/project-member-detail.component';
import { ProjectMemberUpdateComponent } from './update/project-member-update.component';
import ProjectMemberResolve from './route/project-member-routing-resolve.service';

const projectMemberRoute: Routes = [
  {
    path: '',
    component: ProjectMemberComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ProjectMemberDetailComponent,
    resolve: {
      projectMember: ProjectMemberResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ProjectMemberUpdateComponent,
    resolve: {
      projectMember: ProjectMemberResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ProjectMemberUpdateComponent,
    resolve: {
      projectMember: ProjectMemberResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default projectMemberRoute;
