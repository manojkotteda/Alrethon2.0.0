import { Component, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { DashboardService } from '../dashboard.service';
import { MatTableDataSource, MatPaginator, MatSort } from '@angular/material';
import { switchMap, takeUntil, catchError, map } from 'rxjs/operators';
import { Observable, of, Subject, Subscription, timer } from 'rxjs';
import { MeterData } from 'src/app/modals/meterData';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit,OnDestroy {

  constructor(private dashboardService: DashboardService,private _route:ActivatedRoute) { 

    this.chartData = this._route.snapshot.data['bigChartResolvedData'];

   
      this.chartData.data.forEach(ele =>{

        if(ele.deviceId=="DEVICE_1"){
          this.bigChart[0].data.push(ele.energyConsumed);
          //console.log("dev1" + ele.energyConsumed)
        }
      })
      console.log(this.bigChart)

  }

  chartData:any;

  bigChart = [{
    name: 'DEVICE_1',
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

  
  ngOnInit() {
    this.cards = this.dashboardService.cards();
    this.dataSource.paginator = this.paginator;
    
    this.subscription = timer(0, 10000).pipe(
      switchMap(() => this.dashboardService.getAllMeterData())
    ).subscribe(result => {
      this.dataSource.data = result.data;
      console.log(this.bigChart)
    });
  }

  subscription: Subscription;
  ngOnDestroy(){
    this.subscription.unsubscribe();
  }
  


}
