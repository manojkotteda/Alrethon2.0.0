import { Component, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { DashboardService } from '../dashboard.service';
import { MatTableDataSource, MatPaginator, MatSort } from '@angular/material';
import { switchMap, takeUntil, catchError, map } from 'rxjs/operators';
import { Observable, of, Subject, Subscription, timer } from 'rxjs';
import { MeterData } from 'src/app/modals/meterData';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit,OnDestroy {

  bigChart = [{
    name: 'DEVICE_1',
    data: [0,0.1,2]
  }, {
    name: 'DEVICE_2',
    data: []
  }, {
    name: 'DEVICE_3',
    data: []
  }];
  cards = [];

  displayedColumns: string[] = ['deviceId', 'energyConsumed', 'deviceBattery', 'timeStamp'];
  dataSource = new MatTableDataSource<MeterData>();

  @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;

  @ViewChild(MatSort, { static: true }) sort: MatSort;

  ngAfterViewInit() {
    this.dataSource.sort = this.sort;
  }

  constructor(private dashboardService: DashboardService) { }

  ngOnInit() {
    this.cards = this.dashboardService.cards();
    this.dataSource.paginator = this.paginator;
    
    this.subscription = timer(0, 10000).pipe(
      switchMap(() => this.dashboardService.getAllMeterData())
    ).subscribe(result => {
      this.dataSource.data = result.data;
      
      result.data.forEach(ele =>{

        if(ele.deviceId=="DEVICE_1"){
          this.bigChart[0].data.push(ele.energyConsumed);
          //console.log("dev1" + ele.energyConsumed)
        }else if(ele.deviceId=="DEVICE_2"){
          this.bigChart[1].data.push(ele.energyConsumed);
          //console.log("dev2" + ele.energyConsumed)
        }else{
          this.bigChart[2].data.push(ele.energyConsumed);
          //console.log("dev3" + ele.energyConsumed)
        }
      })
      console.log(this.bigChart)
    });
  }

  subscription: Subscription;
  ngOnDestroy(){
    this.subscription.unsubscribe();
  }
  


}
