import { TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness, RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { ProjectMemberDetailComponent } from './project-member-detail.component';

describe('ProjectMember Management Detail Component', () => {
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ProjectMemberDetailComponent, RouterTestingModule.withRoutes([], { bindToComponentInputs: true })],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              component: ProjectMemberDetailComponent,
              resolve: { projectMember: () => of({ id: 123 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(ProjectMemberDetailComponent, '')
      .compileComponents();
  });

  describe('OnInit', () => {
    it('Should load projectMember on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', ProjectMemberDetailComponent);

      // THEN
      expect(instance.projectMember).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
