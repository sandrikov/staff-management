import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { ProjectMemberService } from '../service/project-member.service';

import { ProjectMemberComponent } from './project-member.component';

describe('ProjectMember Management Component', () => {
  let comp: ProjectMemberComponent;
  let fixture: ComponentFixture<ProjectMemberComponent>;
  let service: ProjectMemberService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [
        RouterTestingModule.withRoutes([{ path: 'project-member', component: ProjectMemberComponent }]),
        HttpClientTestingModule,
        ProjectMemberComponent,
      ],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: {
            data: of({
              defaultSort: 'id,asc',
            }),
            queryParamMap: of(
              jest.requireActual('@angular/router').convertToParamMap({
                page: '1',
                size: '1',
                sort: 'id,desc',
              }),
            ),
            snapshot: { queryParams: {} },
          },
        },
      ],
    })
      .overrideTemplate(ProjectMemberComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(ProjectMemberComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(ProjectMemberService);

    const headers = new HttpHeaders();
    jest.spyOn(service, 'query').mockReturnValue(
      of(
        new HttpResponse({
          body: [{ id: 123 }],
          headers,
        }),
      ),
    );
  });

  it('Should call load all on init', () => {
    // WHEN
    comp.ngOnInit();

    // THEN
    expect(service.query).toHaveBeenCalled();
    expect(comp.projectMembers?.[0]).toEqual(expect.objectContaining({ id: 123 }));
  });

  describe('trackId', () => {
    it('Should forward to projectMemberService', () => {
      const entity = { id: 123 };
      jest.spyOn(service, 'getProjectMemberIdentifier');
      const id = comp.trackId(0, entity);
      expect(service.getProjectMemberIdentifier).toHaveBeenCalledWith(entity);
      expect(id).toBe(entity.id);
    });
  });
});
