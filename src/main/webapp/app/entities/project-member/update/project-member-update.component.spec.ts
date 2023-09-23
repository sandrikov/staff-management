import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { IProject } from 'app/entities/project/project.model';
import { ProjectService } from 'app/entities/project/service/project.service';
import { IRole } from 'app/entities/role/role.model';
import { RoleService } from 'app/entities/role/service/role.service';
import { IEmployee } from 'app/entities/employee/employee.model';
import { EmployeeService } from 'app/entities/employee/service/employee.service';
import { IProjectMember } from '../project-member.model';
import { ProjectMemberService } from '../service/project-member.service';
import { ProjectMemberFormService } from './project-member-form.service';

import { ProjectMemberUpdateComponent } from './project-member-update.component';

describe('ProjectMember Management Update Component', () => {
  let comp: ProjectMemberUpdateComponent;
  let fixture: ComponentFixture<ProjectMemberUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let projectMemberFormService: ProjectMemberFormService;
  let projectMemberService: ProjectMemberService;
  let projectService: ProjectService;
  let roleService: RoleService;
  let employeeService: EmployeeService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([]), ProjectMemberUpdateComponent],
      providers: [
        FormBuilder,
        {
          provide: ActivatedRoute,
          useValue: {
            params: from([{}]),
          },
        },
      ],
    })
      .overrideTemplate(ProjectMemberUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(ProjectMemberUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    projectMemberFormService = TestBed.inject(ProjectMemberFormService);
    projectMemberService = TestBed.inject(ProjectMemberService);
    projectService = TestBed.inject(ProjectService);
    roleService = TestBed.inject(RoleService);
    employeeService = TestBed.inject(EmployeeService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call project query and add missing value', () => {
      const projectMember: IProjectMember = { id: 456 };
      const project: IProject = { id: 31437 };
      projectMember.project = project;

      const projectCollection: IProject[] = [{ id: 16656 }];
      jest.spyOn(projectService, 'query').mockReturnValue(of(new HttpResponse({ body: projectCollection })));
      const expectedCollection: IProject[] = [project, ...projectCollection];
      jest.spyOn(projectService, 'addProjectToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ projectMember });
      comp.ngOnInit();

      expect(projectService.query).toHaveBeenCalled();
      expect(projectService.addProjectToCollectionIfMissing).toHaveBeenCalledWith(projectCollection, project);
      expect(comp.projectsCollection).toEqual(expectedCollection);
    });

    it('Should call role query and add missing value', () => {
      const projectMember: IProjectMember = { id: 456 };
      const role: IRole = { id: 30591 };
      projectMember.role = role;

      const roleCollection: IRole[] = [{ id: 26596 }];
      jest.spyOn(roleService, 'query').mockReturnValue(of(new HttpResponse({ body: roleCollection })));
      const expectedCollection: IRole[] = [role, ...roleCollection];
      jest.spyOn(roleService, 'addRoleToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ projectMember });
      comp.ngOnInit();

      expect(roleService.query).toHaveBeenCalled();
      expect(roleService.addRoleToCollectionIfMissing).toHaveBeenCalledWith(roleCollection, role);
      expect(comp.rolesCollection).toEqual(expectedCollection);
    });

    it('Should call employee query and add missing value', () => {
      const projectMember: IProjectMember = { id: 456 };
      const employee: IEmployee = { id: 6809 };
      projectMember.employee = employee;

      const employeeCollection: IEmployee[] = [{ id: 21364 }];
      jest.spyOn(employeeService, 'query').mockReturnValue(of(new HttpResponse({ body: employeeCollection })));
      const expectedCollection: IEmployee[] = [employee, ...employeeCollection];
      jest.spyOn(employeeService, 'addEmployeeToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ projectMember });
      comp.ngOnInit();

      expect(employeeService.query).toHaveBeenCalled();
      expect(employeeService.addEmployeeToCollectionIfMissing).toHaveBeenCalledWith(employeeCollection, employee);
      expect(comp.employeesCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const projectMember: IProjectMember = { id: 456 };
      const project: IProject = { id: 25770 };
      projectMember.project = project;
      const role: IRole = { id: 13358 };
      projectMember.role = role;
      const employee: IEmployee = { id: 342 };
      projectMember.employee = employee;

      activatedRoute.data = of({ projectMember });
      comp.ngOnInit();

      expect(comp.projectsCollection).toContain(project);
      expect(comp.rolesCollection).toContain(role);
      expect(comp.employeesCollection).toContain(employee);
      expect(comp.projectMember).toEqual(projectMember);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IProjectMember>>();
      const projectMember = { id: 123 };
      jest.spyOn(projectMemberFormService, 'getProjectMember').mockReturnValue(projectMember);
      jest.spyOn(projectMemberService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ projectMember });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: projectMember }));
      saveSubject.complete();

      // THEN
      expect(projectMemberFormService.getProjectMember).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(projectMemberService.update).toHaveBeenCalledWith(expect.objectContaining(projectMember));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IProjectMember>>();
      const projectMember = { id: 123 };
      jest.spyOn(projectMemberFormService, 'getProjectMember').mockReturnValue({ id: null });
      jest.spyOn(projectMemberService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ projectMember: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: projectMember }));
      saveSubject.complete();

      // THEN
      expect(projectMemberFormService.getProjectMember).toHaveBeenCalled();
      expect(projectMemberService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IProjectMember>>();
      const projectMember = { id: 123 };
      jest.spyOn(projectMemberService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ projectMember });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(projectMemberService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });

  describe('Compare relationships', () => {
    describe('compareProject', () => {
      it('Should forward to projectService', () => {
        const entity = { id: 123 };
        const entity2 = { id: 456 };
        jest.spyOn(projectService, 'compareProject');
        comp.compareProject(entity, entity2);
        expect(projectService.compareProject).toHaveBeenCalledWith(entity, entity2);
      });
    });

    describe('compareRole', () => {
      it('Should forward to roleService', () => {
        const entity = { id: 123 };
        const entity2 = { id: 456 };
        jest.spyOn(roleService, 'compareRole');
        comp.compareRole(entity, entity2);
        expect(roleService.compareRole).toHaveBeenCalledWith(entity, entity2);
      });
    });

    describe('compareEmployee', () => {
      it('Should forward to employeeService', () => {
        const entity = { id: 123 };
        const entity2 = { id: 456 };
        jest.spyOn(employeeService, 'compareEmployee');
        comp.compareEmployee(entity, entity2);
        expect(employeeService.compareEmployee).toHaveBeenCalledWith(entity, entity2);
      });
    });
  });
});
