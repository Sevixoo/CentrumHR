package com.centrumhr.desktop.di;

import com.centrumhr.application.application.account.AccountUseCaseFactory;
import com.centrumhr.application.application.account.AccountUseCaseFactory_Factory;
import com.centrumhr.application.application.account.IAccountService;
import com.centrumhr.application.application.account.ILoginService;
import com.centrumhr.application.application.common.IExecutor;
import com.centrumhr.application.application.common.IHandler;
import com.centrumhr.application.application.department.DepartmentUseCaseFactory;
import com.centrumhr.application.application.department.DepartmentUseCaseFactory_Factory;
import com.centrumhr.application.application.employee.EmployeeUseCaseFactory;
import com.centrumhr.application.application.employee.EmployeeUseCaseFactory_Factory;
import com.centrumhr.application.application.shedule.ScheduleUseCaseFactory;
import com.centrumhr.application.application.shedule.ScheduleUseCaseFactory_Factory;
import com.centrumhr.application.application.sync.IDataBaseService;
import com.centrumhr.application.application.sync.SyncUseCaseFactory;
import com.centrumhr.application.application.sync.SyncUseCaseFactory_Factory;
import com.centrumhr.application.application.workFunction.WorkFunctionUseCaseFactory;
import com.centrumhr.application.application.workFunction.WorkFunctionUseCaseFactory_Factory;
import com.centrumhr.application.presenter.AddEmployeePresenter;
import com.centrumhr.application.presenter.AddEmployeePresenter_Factory;
import com.centrumhr.application.presenter.EmployeeListPresenter;
import com.centrumhr.application.presenter.EmployeeListPresenter_Factory;
import com.centrumhr.application.presenter.EmployeePresenter;
import com.centrumhr.application.presenter.EmployeePresenter_Factory;
import com.centrumhr.application.presenter.SplashPresenter;
import com.centrumhr.application.presenter.SplashPresenter_Factory;
import com.centrumhr.application.presenter.department.AddDepartmentPresenter;
import com.centrumhr.application.presenter.department.AddDepartmentPresenter_Factory;
import com.centrumhr.application.presenter.department.DepartmentPresenter;
import com.centrumhr.application.presenter.department.DepartmentPresenter_Factory;
import com.centrumhr.application.presenter.shedule.AddAttendancePlanPresenter;
import com.centrumhr.application.presenter.shedule.AddAttendancePlanPresenter_Factory;
import com.centrumhr.application.presenter.shedule.AttendancePlanPickerPresenter;
import com.centrumhr.application.presenter.shedule.AttendancePlanPickerPresenter_Factory;
import com.centrumhr.application.presenter.shedule.AttendancePlanPresenter;
import com.centrumhr.application.presenter.shedule.AttendancePlanPresenter_Factory;
import com.centrumhr.data.domain.IAttendanceDayRepository;
import com.centrumhr.data.domain.IAttendanceEmployeeRepository;
import com.centrumhr.data.domain.IAttendancePlanRepository;
import com.centrumhr.data.domain.IDepartmentRepository;
import com.centrumhr.data.domain.IEmployeeDepartmentRepository;
import com.centrumhr.data.domain.IEmployeeRepository;
import com.centrumhr.data.domain.IWorkFunctionRepository;
import com.centrumhr.data.importer.AttendancePlanImporter;
import com.centrumhr.data.importer.EmployeeImporter;
import com.centrumhr.data.model.attendance.AttendancePlanFactory;
import com.centrumhr.data.model.employment.EmployeeFactory;
import com.centrumhr.data.orm.UnitOfWork;
import com.centrumhr.desktop.data.RepositoryFactory;
import com.centrumhr.desktop.ui.employee.AddEmployeeController;
import com.centrumhr.desktop.ui.employee.AddEmployeeController_MembersInjector;
import com.centrumhr.desktop.ui.employee.EmployeeController;
import com.centrumhr.desktop.ui.employee.EmployeeController_MembersInjector;
import com.centrumhr.desktop.ui.employee.EmployeeListController;
import com.centrumhr.desktop.ui.employee.EmployeeListController_MembersInjector;
import com.centrumhr.desktop.ui.schedule.creator.AddScheduleController;
import com.centrumhr.desktop.ui.schedule.creator.AddScheduleController_MembersInjector;
import com.centrumhr.desktop.ui.schedule.planner.SchedulePlannerController;
import com.centrumhr.desktop.ui.schedule.planner.SchedulePlannerController_MembersInjector;
import com.centrumhr.desktop.ui.start.SplashController;
import com.centrumhr.desktop.ui.start.SplashController_MembersInjector;
import dagger.MembersInjector;
import dagger.internal.MembersInjectors;
import dagger.internal.ScopedProvider;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class DaggerAppComponent implements AppComponent {
  private Provider<IAccountService> provideAccountServiceProvider;
  private Provider<ILoginService> provideLoginServiceProvider;
  private Provider<IExecutor> provideThreadExecutorProvider;
  private Provider<IHandler> providePostExecutionThreadProvider;
  private Provider<AccountUseCaseFactory> accountUseCaseFactoryProvider;
  private Provider<IDataBaseService> provideIDataBaseServiceProvider;
  private Provider<SyncUseCaseFactory> syncUseCaseFactoryProvider;
  private Provider<SplashPresenter> splashPresenterProvider;
  private MembersInjector<SplashController> splashControllerMembersInjector;

  private DaggerAppComponent(Builder builder) {  
    assert builder != null;
    initialize(builder);
  }

  public static Builder builder() {  
    return new Builder();
  }

  private void initialize(final Builder builder) {  
    this.provideAccountServiceProvider = ApplicationModule_ProvideAccountServiceFactory.create(builder.applicationModule);
    this.provideLoginServiceProvider = ApplicationModule_ProvideLoginServiceFactory.create(builder.applicationModule);
    this.provideThreadExecutorProvider = ApplicationModule_ProvideThreadExecutorFactory.create(builder.applicationModule);
    this.providePostExecutionThreadProvider = ApplicationModule_ProvidePostExecutionThreadFactory.create(builder.applicationModule);
    this.accountUseCaseFactoryProvider = AccountUseCaseFactory_Factory.create(provideAccountServiceProvider, provideLoginServiceProvider, provideThreadExecutorProvider, providePostExecutionThreadProvider);
    this.provideIDataBaseServiceProvider = ScopedProvider.create(ApplicationModule_ProvideIDataBaseServiceFactory.create(builder.applicationModule));
    this.syncUseCaseFactoryProvider = SyncUseCaseFactory_Factory.create(provideThreadExecutorProvider, providePostExecutionThreadProvider, provideIDataBaseServiceProvider);
    this.splashPresenterProvider = SplashPresenter_Factory.create(accountUseCaseFactoryProvider, syncUseCaseFactoryProvider, provideIDataBaseServiceProvider);
    this.splashControllerMembersInjector = SplashController_MembersInjector.create((MembersInjector) MembersInjectors.noOp(), splashPresenterProvider);
  }

  @Override
  public void inject(SplashController controller) {  
    splashControllerMembersInjector.injectMembers(controller);
  }

  @Override
  public SplashPresenter getSplashPresenter() {  
    return splashPresenterProvider.get();
  }

  @Override
  public IDataBaseService getDataBaseService() {  
    return provideIDataBaseServiceProvider.get();
  }

  @Override
  public LoggedAccountComponent getLoggedAccountComponent(DataBaseModule dataBaseModule, AccountModule accountModule) {  
    return new LoggedAccountComponentImpl(dataBaseModule, accountModule);
  }

  public static final class Builder {
    private ApplicationModule applicationModule;
  
    private Builder() {  
    }
  
    public AppComponent build() {  
      if (applicationModule == null) {
        throw new IllegalStateException("applicationModule must be set");
      }
      return new DaggerAppComponent(this);
    }
  
    public Builder applicationModule(ApplicationModule applicationModule) {  
      if (applicationModule == null) {
        throw new NullPointerException("applicationModule");
      }
      this.applicationModule = applicationModule;
      return this;
    }
  }

  private final class LoggedAccountComponentImpl implements LoggedAccountComponent {
    private final DataBaseModule dataBaseModule;
    private final AccountModule accountModule;
    private final EmploymentModule employmentModule;
    private Provider<EmployeeFactory> provideEmployeeFactoryProvider;
    private Provider<UnitOfWork> providesUnitOfWorkProvider;
    private Provider<RepositoryFactory> provideRepositoryFactoryProvider;
    private Provider<IDepartmentRepository> provideDepartmentRepositoryProvider;
    private Provider<IEmployeeRepository> provideEmployeeRepositoryProvider;
    private Provider<IWorkFunctionRepository> provideWorkFunctionRepositoryProvider;
    private Provider<IEmployeeDepartmentRepository> provideEmployeeDepartmentRepositoryProvider;
    private Provider<EmployeeImporter> privateEmployeeImporterProvider;
    private Provider<EmployeeUseCaseFactory> employeeUseCaseFactoryProvider;
    private Provider<DepartmentUseCaseFactory> departmentUseCaseFactoryProvider;
    private Provider<WorkFunctionUseCaseFactory> workFunctionUseCaseFactoryProvider;
    private Provider<AddEmployeePresenter> addEmployeePresenterProvider;
    private MembersInjector<AddEmployeeController> addEmployeeControllerMembersInjector;
    private Provider<EmployeeListPresenter> employeeListPresenterProvider;
    private MembersInjector<EmployeeListController> employeeListControllerMembersInjector;
    private Provider<IAttendanceEmployeeRepository> provideAttendanceEmployeeRepositoryProvider;
    private Provider<IAttendancePlanRepository> provideAttendancePlanRepositoryProvider;
    private Provider<IAttendanceDayRepository> provideAttendanceDayRepositoryProvider;
    private Provider<AttendancePlanImporter> provideAttendancePlanImporterProvider;
    private Provider<AttendancePlanFactory> provideAttendancePlanFactoryProvider;
    private Provider<ScheduleUseCaseFactory> scheduleUseCaseFactoryProvider;
    private Provider<AddAttendancePlanPresenter> addAttendancePlanPresenterProvider;
    private MembersInjector<AddScheduleController> addScheduleControllerMembersInjector;
    private Provider<EmployeePresenter> employeePresenterProvider;
    private MembersInjector<EmployeeController> employeeControllerMembersInjector;
    private Provider<AttendancePlanPresenter> attendancePlanPresenterProvider;
    private MembersInjector<SchedulePlannerController> schedulePlannerControllerMembersInjector;
    private Provider<AttendancePlanPickerPresenter> attendancePlanPickerPresenterProvider;
    private Provider<DepartmentPresenter> departmentPresenterProvider;
    private Provider<AddDepartmentPresenter> addDepartmentPresenterProvider;
    private MembersInjector<SplashController> splashControllerMembersInjector;
  
    private LoggedAccountComponentImpl(DataBaseModule dataBaseModule, AccountModule accountModule) {  
      if (dataBaseModule == null) {
        throw new NullPointerException();
      }
      this.dataBaseModule = dataBaseModule;
      if (accountModule == null) {
        throw new NullPointerException();
      }
      this.accountModule = accountModule;
      this.employmentModule = new EmploymentModule();
      initialize();
    }
  
    private void initialize() {  
      this.provideEmployeeFactoryProvider = EmploymentModule_ProvideEmployeeFactoryFactory.create(employmentModule);
      this.providesUnitOfWorkProvider = DataBaseModule_ProvidesUnitOfWorkFactory.create(dataBaseModule);
      this.provideRepositoryFactoryProvider = DataBaseModule_ProvideRepositoryFactoryFactory.create(dataBaseModule, providesUnitOfWorkProvider);
      this.provideDepartmentRepositoryProvider = DataBaseModule_ProvideDepartmentRepositoryFactory.create(dataBaseModule, provideRepositoryFactoryProvider);
      this.provideEmployeeRepositoryProvider = DataBaseModule_ProvideEmployeeRepositoryFactory.create(dataBaseModule, provideRepositoryFactoryProvider);
      this.provideWorkFunctionRepositoryProvider = DataBaseModule_ProvideWorkFunctionRepositoryFactory.create(dataBaseModule, provideRepositoryFactoryProvider);
      this.provideEmployeeDepartmentRepositoryProvider = DataBaseModule_ProvideEmployeeDepartmentRepositoryFactory.create(dataBaseModule, provideRepositoryFactoryProvider);
      this.privateEmployeeImporterProvider = DataBaseModule_PrivateEmployeeImporterFactory.create(dataBaseModule, provideDepartmentRepositoryProvider, provideEmployeeRepositoryProvider, provideWorkFunctionRepositoryProvider, provideEmployeeDepartmentRepositoryProvider);
      this.employeeUseCaseFactoryProvider = EmployeeUseCaseFactory_Factory.create(DaggerAppComponent.this.provideThreadExecutorProvider, DaggerAppComponent.this.providePostExecutionThreadProvider, provideEmployeeFactoryProvider, privateEmployeeImporterProvider, provideEmployeeRepositoryProvider);
      this.departmentUseCaseFactoryProvider = DepartmentUseCaseFactory_Factory.create(DaggerAppComponent.this.provideThreadExecutorProvider, DaggerAppComponent.this.providePostExecutionThreadProvider, provideDepartmentRepositoryProvider);
      this.workFunctionUseCaseFactoryProvider = WorkFunctionUseCaseFactory_Factory.create(DaggerAppComponent.this.provideThreadExecutorProvider, DaggerAppComponent.this.providePostExecutionThreadProvider, provideWorkFunctionRepositoryProvider);
      this.addEmployeePresenterProvider = AddEmployeePresenter_Factory.create(employeeUseCaseFactoryProvider, departmentUseCaseFactoryProvider, workFunctionUseCaseFactoryProvider);
      this.addEmployeeControllerMembersInjector = AddEmployeeController_MembersInjector.create((MembersInjector) MembersInjectors.noOp(), addEmployeePresenterProvider);
      this.employeeListPresenterProvider = EmployeeListPresenter_Factory.create(employeeUseCaseFactoryProvider);
      this.employeeListControllerMembersInjector = EmployeeListController_MembersInjector.create((MembersInjector) MembersInjectors.noOp(), employeeListPresenterProvider);
      this.provideAttendanceEmployeeRepositoryProvider = DataBaseModule_ProvideAttendanceEmployeeRepositoryFactory.create(dataBaseModule, provideRepositoryFactoryProvider);
      this.provideAttendancePlanRepositoryProvider = DataBaseModule_ProvideAttendancePlanRepositoryFactory.create(dataBaseModule, provideRepositoryFactoryProvider);
      this.provideAttendanceDayRepositoryProvider = DataBaseModule_ProvideAttendanceDayRepositoryFactory.create(dataBaseModule, provideRepositoryFactoryProvider);
      this.provideAttendancePlanImporterProvider = DataBaseModule_ProvideAttendancePlanImporterFactory.create(dataBaseModule, provideAttendanceEmployeeRepositoryProvider, provideAttendancePlanRepositoryProvider, provideAttendanceDayRepositoryProvider);
      this.provideAttendancePlanFactoryProvider = EmploymentModule_ProvideAttendancePlanFactoryFactory.create(employmentModule);
      this.scheduleUseCaseFactoryProvider = ScheduleUseCaseFactory_Factory.create(DaggerAppComponent.this.provideThreadExecutorProvider, DaggerAppComponent.this.providePostExecutionThreadProvider, provideAttendancePlanImporterProvider, provideAttendancePlanFactoryProvider, provideEmployeeRepositoryProvider, provideAttendancePlanRepositoryProvider);
      this.addAttendancePlanPresenterProvider = AddAttendancePlanPresenter_Factory.create(scheduleUseCaseFactoryProvider, employeeUseCaseFactoryProvider);
      this.addScheduleControllerMembersInjector = AddScheduleController_MembersInjector.create((MembersInjector) MembersInjectors.noOp(), addAttendancePlanPresenterProvider);
      this.employeePresenterProvider = EmployeePresenter_Factory.create(employeeUseCaseFactoryProvider);
      this.employeeControllerMembersInjector = EmployeeController_MembersInjector.create((MembersInjector) MembersInjectors.noOp(), employeePresenterProvider);
      this.attendancePlanPresenterProvider = AttendancePlanPresenter_Factory.create(scheduleUseCaseFactoryProvider);
      this.schedulePlannerControllerMembersInjector = SchedulePlannerController_MembersInjector.create((MembersInjector) MembersInjectors.noOp(), attendancePlanPresenterProvider);
      this.attendancePlanPickerPresenterProvider = AttendancePlanPickerPresenter_Factory.create(scheduleUseCaseFactoryProvider);
      this.departmentPresenterProvider = DepartmentPresenter_Factory.create(departmentUseCaseFactoryProvider);
      this.addDepartmentPresenterProvider = AddDepartmentPresenter_Factory.create(departmentUseCaseFactoryProvider);
      this.splashControllerMembersInjector = SplashController_MembersInjector.create((MembersInjector) MembersInjectors.noOp(), DaggerAppComponent.this.splashPresenterProvider);
    }
  
    @Override
    public void inject(AddEmployeeController controller) {  
      addEmployeeControllerMembersInjector.injectMembers(controller);
    }
  
    @Override
    public void inject(EmployeeListController controller) {  
      employeeListControllerMembersInjector.injectMembers(controller);
    }
  
    @Override
    public void inject(AddScheduleController controller) {  
      addScheduleControllerMembersInjector.injectMembers(controller);
    }
  
    @Override
    public void inject(EmployeeController controller) {  
      employeeControllerMembersInjector.injectMembers(controller);
    }
  
    @Override
    public void inject(SchedulePlannerController controller) {  
      schedulePlannerControllerMembersInjector.injectMembers(controller);
    }
  
    @Override
    public AddEmployeePresenter getAddEmployeePresenter() {  
      return addEmployeePresenterProvider.get();
    }
  
    @Override
    public EmployeeListPresenter getEmployeeListPresenter() {  
      return employeeListPresenterProvider.get();
    }
  
    @Override
    public EmployeeUseCaseFactory getEmployeeUseCaseFactory() {  
      return employeeUseCaseFactoryProvider.get();
    }
  
    @Override
    public AttendancePlanPickerPresenter getAttendancePlanPickerPresenter() {  
      return attendancePlanPickerPresenterProvider.get();
    }
  
    @Override
    public DepartmentPresenter getDepartmentPresenter() {  
      return departmentPresenterProvider.get();
    }
  
    @Override
    public AddDepartmentPresenter getAddDepartmentPresenter() {  
      return addDepartmentPresenterProvider.get();
    }
  }
}

