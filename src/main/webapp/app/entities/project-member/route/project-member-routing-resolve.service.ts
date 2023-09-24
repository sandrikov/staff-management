import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of, EMPTY, Observable } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IProjectMember } from '../project-member.model';
import { ProjectMemberService } from '../service/project-member.service';

export const projectMemberResolve = (route: ActivatedRouteSnapshot): Observable<null | IProjectMember> => {
  const id = route.params['id'];
  if (id) {
    return inject(ProjectMemberService)
      .find(id)
      .pipe(
        mergeMap((projectMember: HttpResponse<IProjectMember>) => {
          if (projectMember.body) {
            return of(projectMember.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        }),
      );
  }
  return of(null);
};

export default projectMemberResolve;
