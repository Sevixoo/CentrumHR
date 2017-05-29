package com.centrumhr.desktop.di;

import com.centrumhr.application.account.GetLoggedAccountUseCase;
import com.centrumhr.application.account.GetLoggedAccountUseCase_Factory;
import com.centrumhr.application.account.IAccountService;
import com.centrumhr.application.account.ILoginService;
import com.centrumhr.application.department.AddDepartmentUseCase;
import com.centrumhr.application.department.AddDepartmentUseCase_Factory;
import com.centrumhr.application.department.DeleteDepartmentUseCase;
import com.centrumhr.application.department.DeleteDepartmentUseCase_Factory;
import com.centrumhr.application.department.LoadDepartmentsUseCase;
import com.centrumhr.application.department.LoadDepartmentsUseCase_Factory;
import com.centrumhr.application.employee.AddEmployeeUseCase;
import com.centrumhr.application.employee.AddEmployeeUseCase_Factory;
import com.centrumhr.application.employee.EditEmployeeUseCase;
import com.centrumhr.application.employee.EditEmployeeUseCase_Factory;
import com.centrumhr.application.employee.GetEmployeeUseCase;
import com.centrumhr.application.employee.GetEmployeeUseCase_Factory;
import com.centrumhr.application.employee.LoadEmployeesUseCase;
import com.centrumhr.application.employee.LoadEmployeesUseCase_Factory;
import com.centrumhr.application.employee.RemoveEmployeeUseCase;
import com.centrumhr.application.employee.RemoveEmployeeUseCase_Factory;
import com.centrumhr.application.shedule.ChangeAttendanceNameUseCase;
import com.centrumhr.application.shedule.ChangeAttendanceNameUseCase_Factory;
import com.centrumhr.application.shedule.CreateAttendancePlanUseCase;
import com.centrumhr.application.shedule.CreateAttendancePlanUseCase_Factory;
import com.centrumhr.application.shedule.LoadAttendancePlanUseCase;
import com.centrumhr.application.shedule.LoadAttendancePlanUseCase_Factory;
import com.centrumhr.application.shedule.LoadScheduleListUseCase;
import com.centrumhr.application.shedule.LoadScheduleListUseCase_Factory;
import com.centrumhr.application.shedule.SaveAttendancePlanUseCase;
import com.centrumhr.application.shedule.SaveAttendancePlanUseCase_Factory;
import com.centrumhr.application.shedule.ScheduleCreatorProvider;
import com.centrumhr.application.shedule.ScheduleCreatorProvider_Factory;
import com.centrumhr.application.shedule.SetAttendanceDayTypeUseCase;
import com.centrumhr.application.shedule.SetAttendanceDayTypeUseCase_Factory;
import com.centrumhr.application.sync.CreateDataBaseUseCase;
import com.centrumhr.application.sync.CreateDataBaseUseCase_Factory;
import com.centrumhr.application.sync.IORMLiteDataBaseService;
import com.centrumhr.application.sync.IXMLDataBaseService;
import com.centrumhr.application.sync.StartApplicationUseCase;
import com.centrumhr.application.sync.StartApplicationUseCase_Factory;
import com.centrumhr.application.sync.SyncDataBaseUseCase;
import com.centrumhr.application.sync.SyncDataBaseUseCase_Factory;
import com.centrumhr.application.workFunction.LoadWorkFunctionsUseCase;
import com.centrumhr.application.workFunction.LoadWorkFunctionsUseCase_Factory;
import com.centrumhr.data.core.ormlite.IORMLiteDataBase;
import com.centrumhr.data.importer.EmployeeImporter;
import com.centrumhr.data.importer.EmployeeImporter_Factory;
import com.centrumhr.data.model.employment.EmployeeFactory_Factory;
import com.centrumhr.data.model.employment.IEmployeeRepository;
import com.centrumhr.desktop.ui.employee.AddEmployeeController;
import com.centrumhr.desktop.ui.employee.AddEmployeeController_MembersInjector;
import com.centrumhr.desktop.ui.employee.EmployeeController;
import com.centrumhr.desktop.ui.employee.EmployeeController_MembersInjector;
import com.centrumhr.desktop.ui.employee.EmployeeListController;
import com.centrumhr.desktop.ui.employee.EmployeeListController_MembersInjector;
import com.centrumhr.desktop.ui.employee.presenter.AddEmployeePresenter;
import com.centrumhr.desktop.ui.employee.presenter.AddEmployeePresenter_Factory;
import com.centrumhr.desktop.ui.employee.presenter.EmployeeListPresenter;
import com.centrumhr.desktop.ui.employee.presenter.EmployeeListPresenter_Factory;
import com.centrumhr.desktop.ui.employee.presenter.EmployeePresenter;
import com.centrumhr.desktop.ui.employee.presenter.EmployeePresenter_Factory;
import com.centrumhr.desktop.ui.schedule.creator.AddScheduleController;
import com.centrumhr.desktop.ui.schedule.creator.AddScheduleController_MembersInjector;
import com.centrumhr.desktop.ui.schedule.creator.AddSchedulePresenter;
import com.centrumhr.desktop.ui.schedule.creator.AddSchedulePresenter_Factory;
import com.centrumhr.desktop.ui.schedule.list.ScheduleListController;
import com.centrumhr.desktop.ui.schedule.list.ScheduleListController_MembersInjector;
import com.centrumhr.desktop.ui.schedule.list.ScheduleListPresenter;
import com.centrumhr.desktop.ui.schedule.list.ScheduleListPresenter_Factory;
import com.centrumhr.desktop.ui.schedule.planner.SchedulePlannerController;
import com.centrumhr.desktop.ui.schedule.planner.SchedulePlannerController_MembersInjector;
import com.centrumhr.desktop.ui.schedule.planner.presenter.AttendancePlanPresenter;
import com.centrumhr.desktop.ui.schedule.planner.presenter.AttendancePlanPresenter_Factory;
import com.centrumhr.desktop.ui.settings.department.AddDepartmentController;
import com.centrumhr.desktop.ui.settings.department.AddDepartmentController_MembersInjector;
import com.centrumhr.desktop.ui.settings.department.DepartmentSettingsController;
import com.centrumhr.desktop.ui.settings.department.DepartmentSettingsController_MembersInjector;
import com.centrumhr.desktop.ui.settings.department.presenter.AddDepartmentPresenter;
import com.centrumhr.desktop.ui.settings.department.presenter.AddDepartmentPresenter_Factory;
import com.centrumhr.desktop.ui.settings.department.presenter.DepartmentPresenter;
import com.centrumhr.desktop.ui.settings.department.presenter.DepartmentPresenter_Factory;
import com.centrumhr.desktop.ui.start.SplashController;
import com.centrumhr.desktop.ui.start.SplashController_MembersInjector;
import com.centrumhr.desktop.ui.start.SplashPresenter;
import com.centrumhr.desktop.ui.start.SplashPresenter_Factory;
import com.centrumhr.domain.attendance.AttendancePlanFactory;
import com.centrumhr.domain.attendance.IAttendanceHistoryService;
import com.centrumhr.domain.attendance.IAttendancePlanRepository;
import com.centrumhr.domain.attendance.ICalendarService;
import com.centrumhr.domain.attendance.IProfileService;
import com.centrumhr.domain.schedule.ScheduleService;
import dagger.MembersInjector;
import dagger.internal.MembersInjectors;
import dagger.internal.ScopedProvider;
import javax.annotation.Generated;
import javax.inject.Provider;
import rx.Scheduler;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class DaggerAppComponent implements AppComponent {
  private Provider<IXMLDataBaseService> provideXMLDataBaseServiceProvider;
  private Provider<IORMLiteDataBaseService> provideIDataBaseServiceProvider;
  private Provider<IAccountService> provideAccountServiceProvider;
  private Provider<ILoginService> provideLoginServiceProvider;
  private Provider<GetLoggedAccountUseCase> getLoggedAccountUseCaseProvider;
  private Provider<CreateDataBaseUseCase> createDataBaseUseCaseProvider;
  private Provider<SyncDataBaseUseCase> syncDataBaseUseCaseProvider;
  private Provider<StartApplicationUseCase> startApplicationUseCaseProvider;
  private Provider<SplashPresenter> splashPresenterProvider;
  private MembersInjector<SplashController> splashControllerMembersInjector;
  private Provider<Scheduler> schedulerProvider;
  private Provider<ICalendarService> providesCalendarServiceProvider;
  private Provider<IProfileService> profileServiceProvider;
  private Provider<IAttendanceHistoryService> historyServiceProvider;
  private Provider<AttendancePlanFactory> providesAttendancePlanFactoryProvider;
  private Provider<ScheduleCreatorProvider> scheduleCreatorProvider;

  private DaggerAppComponent(Builder builder) {  
    assert builder != null;
    initialize(builder);
  }

  public static Builder builder() {  
    return new Builder();
  }

  private void initialize(final Builder builder) {  
    this.provideXMLDataBaseServiceProvider = ScopedProvider.create(ApplicationModule_ProvideXMLDataBaseServiceFactory.create(builder.applicationModule));
    this.provideIDataBaseServiceProvider = ScopedProvider.create(ApplicationModule_ProvideIDataBaseServiceFactory.create(builder.applicationModule));
    this.provideAccountServiceProvider = ApplicationModule_ProvideAccountServiceFactory.create(builder.applicationModule);
    this.provideLoginServiceProvider = ApplicationModule_ProvideLoginServiceFactory.create(builder.applicationModule);
    this.getLoggedAccountUseCaseProvider = GetLoggedAccountUseCase_Factory.create((MembersInjector) MembersInjectors.noOp(), provideAccountServiceProvider, provideLoginServiceProvider);
    this.createDataBaseUseCaseProvider = CreateDataBaseUseCase_Factory.create((MembersInjector) MembersInjectors.noOp(), provideIDataBaseServiceProvider, provideXMLDataBaseServiceProvider);
    this.syncDataBaseUseCaseProvider = SyncDataBaseUseCase_Factory.create((MembersInjector) MembersInjectors.noOp());
    this.startApplicationUseCaseProvider = StartApplicationUseCase_Factory.create((MembersInjector) MembersInjectors.noOp());
    this.splashPresenterProvider = SplashPresenter_Factory.create((MembersInjector) MembersInjectors.noOp(), provideXMLDataBaseServiceProvider, provideIDataBaseServiceProvider, getLoggedAccountUseCaseProvider, createDataBaseUseCaseProvider, syncDataBaseUseCaseProvider, startApplicationUseCaseProvider);
    this.splashControllerMembersInjector = SplashController_MembersInjector.create((MembersInjector) MembersInjectors.noOp(), splashPresenterProvider);
    this.schedulerProvider = ApplicationModule_SchedulerFactory.create(builder.applicationModule);
    this.providesCalendarServiceProvider = ApplicationModule_ProvidesCalendarServiceFactory.create(builder.applicationModule);
    this.profileServiceProvider = ApplicationModule_ProfileServiceFactory.create(builder.applicationModule);
    this.historyServiceProvider = ApplicationModule_HistoryServiceFactory.create(builder.applicationModule);
    this.providesAttendancePlanFactoryProvider = ApplicationModule_ProvidesAttendancePlanFactoryFactory.create(builder.applicationModule, providesCalendarServiceProvider, profileServiceProvider, historyServiceProvider);
    this.scheduleCreatorProvider = ScopedProvider.create(ScheduleCreatorProvider_Factory.create());
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
  public IORMLiteDataBaseService getDataBaseService() {  
    return provideIDataBaseServiceProvider.get();
  }

  @Override
  public IXMLDataBaseService getXMLDataBaseService() {  
    return provideXMLDataBaseServiceProvider.get();
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
    private Provider<IORMLiteDataBase> providesORMLiteDataBaseProvider;
    private Provider<LoadWorkFunctionsUseCase> loadWorkFunctionsUseCaseProvider;
    private Provider<LoadDepartmentsUseCase> loadDepartmentsUseCaseProvider;
    private Provider<EmployeeImporter> employeeImporterProvider;
    private Provider<AddEmployeeUseCase> addEmployeeUseCaseProvider;
    private Provider<GetEmployeeUseCase> getEmployeeUseCaseProvider;
    private Provider<EditEmployeeUseCase> editEmployeeUseCaseProvider;
    private Provider<AddEmployeePresenter> addEmployeePresenterProvider;
    private MembersInjector<AddEmployeeController> addEmployeeControllerMembersInjector;
    private Provider<LoadEmployeesUseCase> loadEmployeesUseCaseProvider;
    private Provider<EmployeeListPresenter> employeeListPresenterProvider;
    private MembersInjector<EmployeeListController> employeeListControllerMembersInjector;
    private Provider<IAttendancePlanRepository> providesAttendancePlanRepositoryProvider;
    private Provider<ScheduleService> providesScheduleServiceProvider;
    private Provider<IEmployeeRepository> providesEmployeeRepositoryProvider;
    private Provider<CreateAttendancePlanUseCase> createAttendancePlanUseCaseProvider;
    private Provider<AddSchedulePresenter> addSchedulePresenterProvider;
    private MembersInjector<AddScheduleController> addScheduleControllerMembersInjector;
    private Provider<RemoveEmployeeUseCase> removeEmployeeUseCaseProvider;
    private Provider<EmployeePresenter> employeePresenterProvider;
    private MembersInjector<EmployeeController> employeeControllerMembersInjector;
    private Provider<LoadAttendancePlanUseCase> loadAttendancePlanUseCaseProvider;
    private Provider<SaveAttendancePlanUseCase> saveAttendancePlanUseCaseProvider;
    private Provider<SetAttendanceDayTypeUseCase> setAttendanceDayTypeUseCaseProvider;
    private Provider<ChangeAttendanceNameUseCase> changeAttendanceNameUseCaseProvider;
    private Provider<AttendancePlanPresenter> attendancePlanPresenterProvider;
    private MembersInjector<SchedulePlannerController> schedulePlannerControllerMembersInjector;
    private Provider<DeleteDepartmentUseCase> deleteDepartmentUseCaseProvider;
    private Provider<DepartmentPresenter> departmentPresenterProvider;
    private MembersInjector<DepartmentSettingsController> departmentSettingsControllerMembersInjector;
    private Provider<AddDepartmentUseCase> addDepartmentUseCaseProvider;
    private Provider<AddDepartmentPresenter> addDepartmentPresenterProvider;
    private MembersInjector<AddDepartmentController> addDepartmentControllerMembersInjector;
    private Provider<LoadScheduleListUseCase> loadScheduleListUseCaseProvider;
    private Provider<ScheduleListPresenter> scheduleListPresenterProvider;
    private MembersInjector<ScheduleListController> scheduleListControllerMembersInjector;
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
      initialize1();
    }
  
    private void initialize() {  
      this.providesORMLiteDataBaseProvider = DataBaseModule_ProvidesORMLiteDataBaseFactory.create(dataBaseModule);
      this.loadWorkFunctionsUseCaseProvider = LoadWorkFunctionsUseCase_Factory.create((MembersInjector) MembersInjectors.noOp(), providesORMLiteDataBaseProvider);
      this.loadDepartmentsUseCaseProvider = LoadDepartmentsUseCase_Factory.create((MembersInjector) MembersInjectors.noOp(), providesORMLiteDataBaseProvider);
      this.employeeImporterProvider = EmployeeImporter_Factory.create(providesORMLiteDataBaseProvider);
      this.addEmployeeUseCaseProvider = AddEmployeeUseCase_Factory.create((MembersInjector) MembersInjectors.noOp(), EmployeeFactory_Factory.create(), employeeImporterProvider);
      this.getEmployeeUseCaseProvider = GetEmployeeUseCase_Factory.create((MembersInjector) MembersInjectors.noOp(), providesORMLiteDataBaseProvider);
      this.editEmployeeUseCaseProvider = EditEmployeeUseCase_Factory.create((MembersInjector) MembersInjectors.noOp(), EmployeeFactory_Factory.create(), employeeImporterProvider);
      this.addEmployeePresenterProvider = AddEmployeePresenter_Factory.create((MembersInjector) MembersInjectors.noOp(), loadWorkFunctionsUseCaseProvider, loadDepartmentsUseCaseProvider, addEmployeeUseCaseProvider, getEmployeeUseCaseProvider, editEmployeeUseCaseProvider);
      this.addEmployeeControllerMembersInjector = AddEmployeeController_MembersInjector.create((MembersInjector) MembersInjectors.noOp(), addEmployeePresenterProvider);
      this.loadEmployeesUseCaseProvider = LoadEmployeesUseCase_Factory.create((MembersInjector) MembersInjectors.noOp(), providesORMLiteDataBaseProvider);
      this.employeeListPresenterProvider = EmployeeListPresenter_Factory.create((MembersInjector) MembersInjectors.noOp(), DaggerAppComponent.this.schedulerProvider, loadEmployeesUseCaseProvider);
      this.employeeListControllerMembersInjector = EmployeeListController_MembersInjector.create((MembersInjector) MembersInjectors.noOp(), employeeListPresenterProvider);
      this.providesAttendancePlanRepositoryProvider = DataBaseModule_ProvidesAttendancePlanRepositoryFactory.create(dataBaseModule, DaggerAppComponent.this.providesAttendancePlanFactoryProvider);
      this.providesScheduleServiceProvider = DataBaseModule_ProvidesScheduleServiceFactory.create(dataBaseModule, DaggerAppComponent.this.providesAttendancePlanFactoryProvider, DaggerAppComponent.this.providesCalendarServiceProvider);
      this.providesEmployeeRepositoryProvider = DataBaseModule_ProvidesEmployeeRepositoryFactory.create(dataBaseModule, providesORMLiteDataBaseProvider);
      this.createAttendancePlanUseCaseProvider = CreateAttendancePlanUseCase_Factory.create((MembersInjector) MembersInjectors.noOp(), providesAttendancePlanRepositoryProvider, providesScheduleServiceProvider, DaggerAppComponent.this.scheduleCreatorProvider, providesEmployeeRepositoryProvider);
      this.addSchedulePresenterProvider = AddSchedulePresenter_Factory.create((MembersInjector) MembersInjectors.noOp(), DaggerAppComponent.this.providesCalendarServiceProvider, loadEmployeesUseCaseProvider, createAttendancePlanUseCaseProvider);
      this.addScheduleControllerMembersInjector = AddScheduleController_MembersInjector.create((MembersInjector) MembersInjectors.noOp(), addSchedulePresenterProvider);
      this.removeEmployeeUseCaseProvider = RemoveEmployeeUseCase_Factory.create((MembersInjector) MembersInjectors.noOp(), providesORMLiteDataBaseProvider);
      this.employeePresenterProvider = EmployeePresenter_Factory.create((MembersInjector) MembersInjectors.noOp(), removeEmployeeUseCaseProvider);
      this.employeeControllerMembersInjector = EmployeeController_MembersInjector.create((MembersInjector) MembersInjectors.noOp(), employeePresenterProvider);
      this.loadAttendancePlanUseCaseProvider = LoadAttendancePlanUseCase_Factory.create((MembersInjector) MembersInjectors.noOp(), providesAttendancePlanRepositoryProvider, DaggerAppComponent.this.scheduleCreatorProvider);
      this.saveAttendancePlanUseCaseProvider = SaveAttendancePlanUseCase_Factory.create((MembersInjector) MembersInjectors.noOp(), providesAttendancePlanRepositoryProvider, DaggerAppComponent.this.scheduleCreatorProvider);
      this.setAttendanceDayTypeUseCaseProvider = SetAttendanceDayTypeUseCase_Factory.create((MembersInjector) MembersInjectors.noOp(), providesScheduleServiceProvider, DaggerAppComponent.this.scheduleCreatorProvider);
      this.changeAttendanceNameUseCaseProvider = ChangeAttendanceNameUseCase_Factory.create((MembersInjector) MembersInjectors.noOp(), DaggerAppComponent.this.scheduleCreatorProvider);
      this.attendancePlanPresenterProvider = AttendancePlanPresenter_Factory.create((MembersInjector) MembersInjectors.noOp(), DaggerAppComponent.this.providesCalendarServiceProvider, loadAttendancePlanUseCaseProvider, saveAttendancePlanUseCaseProvider, setAttendanceDayTypeUseCaseProvider, changeAttendanceNameUseCaseProvider);
      this.schedulePlannerControllerMembersInjector = SchedulePlannerController_MembersInjector.create((MembersInjector) MembersInjectors.noOp(), attendancePlanPresenterProvider);
      this.deleteDepartmentUseCaseProvider = DeleteDepartmentUseCase_Factory.create((MembersInjector) MembersInjectors.noOp(), providesORMLiteDataBaseProvider);
      this.departmentPresenterProvider = DepartmentPresenter_Factory.create((MembersInjector) MembersInjectors.noOp(), loadDepartmentsUseCaseProvider, deleteDepartmentUseCaseProvider);
      this.departmentSettingsControllerMembersInjector = DepartmentSettingsController_MembersInjector.create((MembersInjector) MembersInjectors.noOp(), departmentPresenterProvider);
      this.addDepartmentUseCaseProvider = AddDepartmentUseCase_Factory.create((MembersInjector) MembersInjectors.noOp(), providesORMLiteDataBaseProvider);
      this.addDepartmentPresenterProvider = AddDepartmentPresenter_Factory.create((MembersInjector) MembersInjectors.noOp(), addDepartmentUseCaseProvider);
      this.addDepartmentControllerMembersInjector = AddDepartmentController_MembersInjector.create((MembersInjector) MembersInjectors.noOp(), addDepartmentPresenterProvider);
      this.loadScheduleListUseCaseProvider = LoadScheduleListUseCase_Factory.create((MembersInjector) MembersInjectors.noOp(), providesAttendancePlanRepositoryProvider);
      this.scheduleListPresenterProvider = ScheduleListPresenter_Factory.create((MembersInjector) MembersInjectors.noOp(), loadScheduleListUseCaseProvider);
      this.scheduleListControllerMembersInjector = ScheduleListController_MembersInjector.create((MembersInjector) MembersInjectors.noOp(), scheduleListPresenterProvider);
    }
  
    private void initialize1() {  
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
    public void inject(DepartmentSettingsController controller) {  
      departmentSettingsControllerMembersInjector.injectMembers(controller);
    }
  
    @Override
    public void inject(AddDepartmentController controller) {  
      addDepartmentControllerMembersInjector.injectMembers(controller);
    }
  
    @Override
    public void inject(ScheduleListController scheduleListController) {  
      scheduleListControllerMembersInjector.injectMembers(scheduleListController);
    }
  }
}

