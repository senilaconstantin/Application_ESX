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
    <v-row>
      <v-col lg="8">
        <v-form ref="form" class="ml-6 mr-6">
          <div id="app">
            <v-app >
              <div>
                <v-text-field
                  label="First Name"
                  type="text"
                  v-model="fnChange"
                />

                <v-text-field
                  label="Last Name"
                  type="text"
                  v-model="lnChange"
                />
                <v-text-field label="Email" type="text" v-model="emailChange" />

                <v-text-field
                  label="Adress"
                  type="text"
                  v-model="adressChange"
                />
                <v-select
                  v-model="variant"
                  :items="items"
                  clearable
                  label="Subscriptions"
                ></v-select>

                <v-btn
                  color="success"
                  class="mr-4"
                  @click="$router.push({ name: back1 })"
                >
                  Back
                </v-btn>
                <v-btn color="success" class="mr-4" @click="update">
                  Update
                </v-btn>
              </div>
            </v-app>
          </div>
        </v-form>
      </v-col>
    </v-row>
  </nav>
</template>

<script>
import axios from "axios";

export default {
  name: "HomeAdminView",
  data() {
    return {
      user: "",
      fnChange: "",
      lnChange: "",
      emailChange: "",
      adressChange: "",
      drawer: false,
      links: [
        { icon: "mdi-home", text: "Home", route: "/homeAdmin" },
        // { icon: "mdi-account-multiple", text: "Users", route: "/admin/users" },
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
      items: ["Basic", "Premium"],
      variant: "",
      users: [],
      lodUserId: window.localStorage.getItem("idU"),
      back1: window.localStorage.getItem("back"),
    };
  },

  async created() {
    var result = await axios.get(
      "http://localhost:8081/users/" + this.lodUserId
    );
    this.user = result.data;
    this.fnChange = this.user.firstName;
    this.lnChange = this.user.lastName;
    this.emailChange = this.user.mail;
    this.adressChange = this.user.adress;

    try {
      var abon = await axios.get(
        "http://localhost:8081/users/getAb/" + this.lodUserId
      );

      //   alert("ceva aiciiii"+this.lodUserId)
      this.variant = abon.data.tipAbonament;
      window.localStorage.setItem("idAbonament", abon.data.id);
      //   alert(abon.data.tipAbonament);
    } catch {
      alert("eroare");
    }
  },

  methods: {
    async update() {
      try {
        await axios.put("http://localhost:8081/users", {
          id: this.user.id,
          firstName: this.fnChange,
          lastName: this.lnChange,
          mail: this.emailChange,
          password: this.user.password,
          adress: this.adressChange,
        });

        // await axios.delete("http:/localhost:8081/users/deleteArc/"+this.user.id);

        // await axios.post("http:/localhost:8081/users/addArc/"+this.user.id+"/"+this.variant);

        // try{
        //     alert(this.variant);
        // }catch(e){
        //     alert("ddd");
        // }
        // alert(result);
        // alert(this.fnChange + " " + this.user.id);
        this.$router.push({ name: this.back1 });
      } catch (e) {
        alert("Email deja existent!");
      }
    },
  },
};
</script>

<style lang="scss" scoped>
input {
  border: 2px solid black;
}
</style>


