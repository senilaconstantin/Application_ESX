import Vue from 'vue'
import VueRouter from 'vue-router'
// import HomeView from '../views/HomeView.vue'
import HomeAdminView from '../views/HomeAdminView.vue'
import LoginView from '../views/LoginView.vue'
import LoginAdminView from '../views/LoginAdminView.vue'
import RegisterView from '../views/RegisterView.vue'
import UpdateView from '../views/UpdateView.vue'
import DeleteView from '../views/DeleteView.vue'

import UsersAdminView from '../views/UsersAdminView.vue'
import UpdateUsersView from '../views/UpdateUsersView.vue'
import AddNewUser from '../views/AddNewUser.vue'
import SubscriptionsAdminView from '../views/SubscriptionsAdminView.vue'

import UpdateSubscriptions from "../views/UpdateSubscriptions.vue"
import GymsAdmin from "../views/GymsAdmin.vue"
import UpdateGymAdmin from "../views/UpdateGymAdmin.vue"
import AddGymAdmin from "../views/AddGymAdmin.vue"
import HomeUsersView from "../views/HomeUsersView.vue"

import SubscriptionsUser from "../views/SubscriptionsUser.vue"
import ViewGymsUser from "../views/ViewGymsUser.vue"
import ReviewsUser from "../views/ReviewsUser.vue"
import AddGymAb from "../views/AddGymAb.vue"
import DeleteGymAb from "../views/DeleteGymAb.vue"

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'login',
    component: LoginView
  },
  /////////////////
  {
    path: '/homeAdmin',
    name: 'homeAdmin',
    component: HomeAdminView
  },
  {
    path: '/admin',
    name: 'loginAdmin',
    component: LoginAdminView
  },
  {
    path: '/admin/users',
    name: 'users',
    component: UsersAdminView
  },
  {
    path: '/admin/subscriptions',
    name: 'subscriptions',
    component: SubscriptionsAdminView
  },
  {
    path: '/admin/subscriptions/update',
    name: 'updateSubscriptions',
    component: UpdateSubscriptions
  },
  {
    path: '/admin/users/addUser',
    name: 'addUser',
    component: AddNewUser
  },
  {
    path: '/admin/user/:id/update',
    name: 'updateAdmin',
    component: UpdateUsersView
  },
  {
    path: '/admin/gyms',
    name: 'gymsAdmin',
    component: GymsAdmin
  },
  {
    path: '/admin/gyms/update',
    name: 'gymsAdminUpdate',
    component: UpdateGymAdmin
  },
  {
    path: '/admin/gyms/add',
    name: 'addGym',
    component: AddGymAdmin
  },
  {
    path: '/admin/subscriptions/update/addGym',
    name: 'addGymAb',
    component: AddGymAb
  },
  {
    path: '/admin/subscriptions/update/deleteGym',
    name: 'deleteGymAb',
    component: DeleteGymAb
  },
  /////////////////////
  // {
  //   path: '/home',
  //   name: 'home',
  //   component: HomeView
  // },
  {
    path: '/home',
    name: 'home',
    component: HomeUsersView
  },

  {
    path: '/home/gyms',
    name: 'gyms',
    component: ViewGymsUser
  },
  {
    path: '/home/subscriptions',
    name: 'subscriptionsU',
    component: SubscriptionsUser
  },
  {
    path: '/home/gym/reviews',
    name: 'reviewsU',
    component: ReviewsUser
  },
  
  {
    path: '/register',
    name: 'register',
    component: RegisterView
  },
  {
    path: '/home/user/:id/update',
    name: 'update',
    component: UpdateView
  },

  {
    path: '/home/user/delete',
    name: 'delete',
    component: DeleteView
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
