import { DataSource } from '@angular/cdk/collections';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { map } from 'rxjs/operators';
import { Observable, of as observableOf, merge } from 'rxjs';
import { ApiService } from '../api.service';

// TODO: Replace this with your own data model type
export interface TableMatchesItem {
  datum: string;
  home: string;
  score: string;
  away: string;
  id: number;
}

// TODO: replace this with real data from your application
const EXAMPLE_DATA: TableMatchesItem[] = [
  {
    datum: "06:59 28.03",
    score: "2:1",
    away: "Russia",
    id: 1,
    home: "Nigeria"
  }, 
  {
    datum: "07:59 29.03",
    score: "0:1",
    away: "Italy",
    id: 1,
    home: "France"
  }
];

/**
 * Data source for the TableMatches view. This class should
 * encapsulate all logic for fetching and manipulating the displayed data
 * (including sorting, pagination, and filtering).
 */
export class TableMatchesDataSource extends DataSource<TableMatchesItem> {
  data: TableMatchesItem[] = [];
  paginator: MatPaginator;
  sort: MatSort;

  constructor(private api: ApiService) {
    super();
  }

  /**
   * Connect this data source to the table. The table will only update when
   * the returned stream emits new items.
   * @returns A stream of the items to be rendered.
   */
  connect(): Observable<TableMatchesItem[]> {
    // Combine everything that affects the rendered data into one update
    // stream for the data-table to consume.
    this.api.getAllMatches().subscribe(data => {
      this.data = data as TableMatchesItem[]
    });

    const dataMutations = [
      this.api.getAllMatches(),
      this.paginator.page,
      this.sort.sortChange
    ];

    return merge(...dataMutations).pipe(map(() => {
      return this.getPagedData(this.getSortedData([...this.data]));
    }));
  }

  /**
   *  Called when the table is being destroyed. Use this function, to clean up
   * any open connections or free any held resources that were set up during connect.
   */
  disconnect() {}

  /**
   * Paginate the data (client-side). If you're using server-side pagination,
   * this would be replaced by requesting the appropriate data from the server.
   */
  private getPagedData(data: TableMatchesItem[]) {
    const startIndex = this.paginator.pageIndex * this.paginator.pageSize;
    return data.splice(startIndex, this.paginator.pageSize);
  }

  /**
   * Sort the data (client-side). If you're using server-side sorting,
   * this would be replaced by requesting the appropriate data from the server.
   */
  private getSortedData(data: TableMatchesItem[]) {
    if (!this.sort.active || this.sort.direction === '') {
      return data;
    }

    return data.sort((a, b) => {
      const isAsc = this.sort.direction === 'asc';
      switch (this.sort.active) {
        case 'datum': return compare(a.datum, b.datum, isAsc);
        case 'home': return compare(a.home, b.home, isAsc);
        case 'score': return compare(a.score, b.score, isAsc);
        case 'away': return compare(a.away, b.away, isAsc);
        case 'id': return compare(+a.id, +b.id, isAsc);
        default: return 0;
      }
    });
  }
}

/** Simple sort comparator for example ID/Name columns (for client-side sorting). */
function compare(a: string | number, b: string | number, isAsc: boolean) {
  return (a < b ? -1 : 1) * (isAsc ? 1 : -1);
}
