import { DataSource } from '@angular/cdk/collections';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { map } from 'rxjs/operators';
import { Observable, of as observableOf, merge } from 'rxjs';
import { ApiService } from '../api.service';
import { Router, ActivatedRoute } from '@angular/router';

// TODO: Replace this with your own data model type
export interface SearchMatchItem {
  datum: string;
  home: string;
  score: string;
  away: string;
  id: number;
}

/**
 * Data source for the SearchMatch view. This class should
 * encapsulate all logic for fetching and manipulating the displayed data
 * (including sorting, pagination, and filtering).
 */
export class SearchMatchDataSource extends DataSource<SearchMatchItem> {
  data: SearchMatchItem[] = [];
  paginator: MatPaginator;
  sort: MatSort;
  name: string;

  constructor(private api: ApiService, private route: ActivatedRoute) {
    super();
  }

  /**
   * Connect this data source to the table. The table will only update when
   * the returned stream emits new items.
   * @returns A stream of the items to be rendered.
   */
  connect(): Observable<SearchMatchItem[]> {
    // Combine everything that affects the rendered data into one update
    // stream for the data-table to consume.
    this.route.params.subscribe(event => {
      this.name = event.name;
    });

    this.api.getSearch(this.name).subscribe(data => {
      this.data = data[1].Zapasy as SearchMatchItem[]
    })

    const dataMutations = [
      this.api.getSearch(this.name),
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
  private getPagedData(data: SearchMatchItem[]) {
    const startIndex = this.paginator.pageIndex * this.paginator.pageSize;
    return data.splice(startIndex, this.paginator.pageSize);
  }

  /**
   * Sort the data (client-side). If you're using server-side sorting,
   * this would be replaced by requesting the appropriate data from the server.
   */
  private getSortedData(data: SearchMatchItem[]) {
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
