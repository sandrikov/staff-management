import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { JobHistoryService } from '../service/job-history.service';

import { JobHistoryComponent } from './job-history.component';

describe('JobHistory Management Component', () => {
  let comp: JobHistoryComponent;
  let fixture: ComponentFixture<JobHistoryComponent>;
  let service: JobHistoryService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [
        RouterTestingModule.withRoutes([{ path: 'job-history', component: JobHistoryComponent }]),
        HttpClientTestingModule,
        JobHistoryComponent,
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
      .overrideTemplate(JobHistoryComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(JobHistoryComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(JobHistoryService);

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
    expect(comp.jobHistories?.[0]).toEqual(expect.objectContaining({ id: 123 }));
  });

  describe('trackId', () => {
    it('Should forward to jobHistoryService', () => {
      const entity = { id: 123 };
      jest.spyOn(service, 'getJobHistoryIdentifier');
      const id = comp.trackId(0, entity);
      expect(service.getJobHistoryIdentifier).toHaveBeenCalledWith(entity);
      expect(id).toBe(entity.id);
    });
  });
});
