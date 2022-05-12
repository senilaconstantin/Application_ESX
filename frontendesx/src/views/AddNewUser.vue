<template>
  <nav>
    <v-toolbar flat app>
      <v-toolbar-side-icon @click="drawer = !drawer" class="grey--text">
        <v-app-bar-nav-icon></v-app-bar-nav-icon>
      </v-toolbar-side-icon>
      <v-toolbar-title class="text black--text">
        <span class="font-weight-light">Admin</span>
        <span class="font-bolt"> ESX</span>
      </v-toolbar-title>
      <v-spacer></v-spacer>
      <v-btn flat color="grey" @click="$router.push({ name: 'loginAdmin' })">
        <span>Sign Out</span>
        <v-icon right>mdi-export</v-icon>
      </v-btn>
    </v-toolbar>

    <v-navigation-drawer app v-model="drawer" class="grey">
      <v-list-item-group
        v-model="group"
        active-class="deep-purple--text text--accent-5"
      >
        <v-list-item
          v-for="link in links"
          :key="link.text"
          router
          :to="link.route"
        >
          <v-list-tile-action>
            <v-icon class="black--text">{{ link.icon }}</v-icon>
          </v-list-tile-action>
          <v-list-tile-content>
            <v-list-tile-title class="white--text">{{
              link.text
            }}</v-list-tile-title>
          </v-list-tile-content>
        </v-list-item>
      </v-list-item-group>
    </v-navigation-drawer>

    <v-form ref="form" class="ml-6 mr-6">
      <v-text-field
        v-model="firstName"
        label="First Name"
        required
      ></v-text-field>

      <v-text-field
        v-model="lastName"
        label="Last Name"
        required
      ></v-text-field>

      <v-text-field v-model="mail" label="Email" required></v-text-field>

      <v-text-field
        v-model="password"
        label="Password"
        type="password"
        required
      ></v-text-field>

      <v-text-field v-model="adress" label="Adress" required></v-text-field>

      <v-select
        v-model="tipAbonament"
        :items="items"
        clearable
        label="Subscriptions"
      ></v-select>

      <v-btn
        color="success"
        class="mr-4"
        @click="$router.push({ name: 'users' })"
      >
        Back
      </v-btn>
      <v-btn color="success" class="mr-4" @click="register"> Add User </v-btn>
    </v-form>
  </nav>
</template>

<script>
import axios from "axios";

export default {
  name: "AddNewUser",
  data() {
    return {
      tipAbonament:"",
      users: [],
      lodUserId: window.localStorage.getItem("idAdmin"),
      dialog: false,
      drawer: false,
      links: [
        { icon: "mdi-home", text: "Home", route: "/homeAdmin" },
        { icon: "mdi-account-multiple", text: "Users", route: "/admin/users" },
        {
          icon: "mdi-clipboard-text",
          text: "Subscriptions",
          route: "/admin/subscriptions",
        },
        {
          icon: "mdi-widgets",
          text: "Gyms",
          route: "/admin/gyms",
        },
      ],
      items: [],
    };
  },

  async created() {
    try{
        var result=await axios.get("http://localhost:8081/abonament/tipAbonament");
        this.items=result.data;
    }catch(e){
      alert("Eroare!");
    }
  },
  methods: {
    async register() {
      try {
        await axios.post("http://localhost:8081/users/ab", {
          firstName: this.firstName,
          lastName: this.lastName,
          mail: this.mail,
          password: this.password,
          adress: this.adress,
          tipAbonament:this.tipAbonament,
        });
        this.$router.push({ name: "users" });
      } catch (e) {
        alert("Email deja existent!");
      }
    },
  },
};
</script>


