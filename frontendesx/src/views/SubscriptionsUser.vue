<template>
  <nav>
    <v-toolbar flat app class="blue">
      <v-toolbar-side-icon @click="drawer = !drawer" class="grey--text">
        <v-app-bar-nav-icon></v-app-bar-nav-icon>
      </v-toolbar-side-icon>
      <v-toolbar-title class="text black--text">
        <span class="font-weight-light">User</span>
        <span class="font-bolt"> ESX</span>
      </v-toolbar-title>
      <v-spacer></v-spacer>
      <v-btn flat color="grey" @click="$router.push({ name: 'login' })">
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
            <v-app>
              <v-list subheader two-line>
                <div v-if="ok == true">
                  <v-subheader inset>My Subscription</v-subheader>

                  <!-- <v-list-item v-for="gym in subscriptions" :key="gym.id"> -->
                  <v-list-item>
                    <v-list-item-avatar>
                      <v-icon class="grey lighten-1" dark> mdi-folder </v-icon>
                    </v-list-item-avatar>

                    <v-list-item-content>
                      <v-list-item-title
                        v-text="subscription.tipAbonament"
                      ></v-list-item-title>

                      <!-- <v-list-item-title
                      v-text="user.lastName"
                    ></v-list-item-title> -->

                      <v-list-item-subtitle
                        v-text="subscription.pret"
                      ></v-list-item-subtitle>
                    </v-list-item-content>
                    <v-list-item-action>
                      <!-- <v-btn icon @click="update(user.id)">
                      <v-icon color="grey lighten-1">mdi-pencil</v-icon>
                    </v-btn> -->
                      <v-btn icon @click="dialog = true">
                        <v-icon color="grey lighten-1">mdi-delete</v-icon>
                      </v-btn>
                      <v-dialog v-model="dialog" max-width="290">
                        <v-card>
                          <v-card-title class="text-h5"> Delete </v-card-title>

                          <v-card-text>
                            Sigur vrei sa stergi abonamentul?
                          </v-card-text>

                          <v-card-actions>
                            <v-spacer></v-spacer>

                            <v-btn
                              color="green darken-1"
                              text
                              @click="dialog = false"
                            >
                              No
                            </v-btn>

                            <v-btn
                              color="green darken-1"
                              text
                              @click="deletee()"
                            >
                              Yes
                            </v-btn>
                          </v-card-actions>
                        </v-card>
                      </v-dialog>
                    </v-list-item-action>
                  </v-list-item>
                  <!-- </v-list-item> -->
                </div>
                <!-- /////////////////////////////////////////////////// -->
                <div v-else-if="ok == false">
                  <v-subheader inset>Chose Subscription</v-subheader>

                  <v-list-item v-for="subs in subscriptions" :key="subs.id">
                    <v-list-item-avatar>
                      <v-icon class="grey lighten-1" dark> mdi-folder </v-icon>
                    </v-list-item-avatar>
                    <v-list-item-content>
                      <v-list-item-title
                        v-text="subs.tipAbonament"
                      ></v-list-item-title>

                      <v-list-item-subtitle v-text="subs.pret">
                      </v-list-item-subtitle>
                    </v-list-item-content>

                    <v-list-item-action>
                      <v-btn icon @click="add(subs.id)">
                        <v-icon color="grey lighten-1">mdi-plus</v-icon>
                      </v-btn>
                    </v-list-item-action>
                  </v-list-item>
                </div>
              </v-list>
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
  name: "SubscriptionsUser",
  data() {
    return {
      subscription: "",
      subscriptions: [],
      dialog: false,
      ok: false,
      // idSubs: "",
      // gyms: [],
      drawer: false,
      links: [
        { icon: "mdi-home", text: "Home", route: "/home" },
        // { icon: "mdi-account-multiple", text: "Users", route: "/home" },
        {
          icon: "mdi-clipboard-text",
          text: "Subscriptions",
          route: "/home/subscriptions",
        },
        {
          icon: "mdi-widgets",
          text: "Gyms",
          route: "/home/gyms",
        },
      ],
      users: [],
      lodUserId: window.localStorage.getItem("idUser"),
    };
  },

  async created() {
    try {
      var resultSubs = await axios.get("http://localhost:8081/abonament/");
      this.subscriptions = resultSubs.data;
    } catch (e) {
      alert("Eroare!");
    }
    this.refresh();
  },

  methods: {
    async deletee() {
      // alert("aicii");
      this.dialog = false;
      try {
        await axios.delete(
          "http://localhost:8081/users/deleteArc/" + this.lodUserId
        );
        this.ok = false;
      } catch (e) {
        alert("Eroare delete!");
      }
    },
    async refresh() {
      try {
        var result = await axios.get(
          "http://localhost:8081/users/getAb/" + this.lodUserId
        );
        this.subscription = result.data;

        if (result.data) {
          // alert("11111");
          this.ok = true;
        }
        ////////////////////////////////
      } catch (e) {
        alert("Eroare1!");
        // this.$router.push({ name: "home" });
      }
    },
    async add(idSubs) {
      try {
        await axios.post("http://localhost:8081/users/addArc/", {
          idUser: this.lodUserId,
          idAbonament: idSubs,
        });
        alert("Abonament ales!");
        this.refresh();
        this.ok = true;
        // this.$router.push({ name: "gymsAdmin" });
      } catch (e) {
        alert("Eroare adaugare Gym!");
      }
    },
    // async update() {
    //   try {
    //     // alert("sigur?");
    //     await axios.put("http://localhost:8081/users", {
    //       id: this.user.id,
    //       firstName: this.fnChange,
    //       lastName: this.lnChange,
    //       mail: this.emailChange,
    //       password: this.user.password,
    //       adress: this.adressChange,
    //     });
    //     // alert(result);
    //     // alert(this.fnChange + " " + this.user.id);
    //     alert("S-a updatat!");
    //     this.$router.push({ name: "home" });
    //   } catch (e) {
    //     alert("Email deja existent!");
    //   }
    // },
  },
};
</script>

<style lang="scss" scoped>
input {
  border: 2px solid black;
}
</style>


