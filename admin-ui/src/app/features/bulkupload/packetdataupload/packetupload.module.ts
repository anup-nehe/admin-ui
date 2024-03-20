import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CreateComponent } from './create/create.component';
import { ViewComponent } from './view/view.component';
import { SingleviewComponent } from './singleview/singleview.component';
import { PacketuploadRoutingModule } from './packetupload-routing.module';
import { SharedModule } from '../../../shared/shared.module';
import { MaterialModule } from 'src/app/shared/material.module';
import { I18nModule } from '../../../../app/i18n.module';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { MatKeyboardModule } from '@ngx-material-keyboard/core';


@NgModule({
  imports: [
    CommonModule,
    PacketuploadRoutingModule,
    SharedModule,
    MaterialModule,
    I18nModule,
    ReactiveFormsModule,
    FormsModule,
    MatKeyboardModule
  ],
  declarations: [CreateComponent, ViewComponent, SingleviewComponent]
})
export class PacketuploadModule { }
