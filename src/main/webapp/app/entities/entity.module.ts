import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'customer',
        loadChildren: () => import('./customer/customer.module').then(m => m.StoreCustomerModule),
      },
      {
        path: 'order-item',
        loadChildren: () => import('./order-item/order-item.module').then(m => m.StoreOrderItemModule),
      },
      {
        path: 'product-order',
        loadChildren: () => import('./product-order/product-order.module').then(m => m.StoreProductOrderModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class StoreEntityModule {}
