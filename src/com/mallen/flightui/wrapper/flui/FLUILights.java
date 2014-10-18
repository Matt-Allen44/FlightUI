/*
	Author: Matthew Allen
	Website: https://github.com/Daz44
	Created by Daz at 10:27:29 PM on 13/10/2014

	THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
	IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
	FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
	AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
	LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
	OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
	THE SOFTWARE.

 */

package com.mallen.flightui.wrapper.flui;

import com.mallen.flightui.wrapper.FLUI_READER;

public class FLUILights {
	public static boolean Nav() {
		if ((FLUI_READER.getInt(0x0D0C) & 0x1) > 0) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean Beacon() {
		if ((FLUI_READER.getInt(0x0D0C) & 0x2) > 0) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean Landing() {
		if ((FLUI_READER.getInt(0x0D0C) & 0x4) > 0) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean Taxi() {
		if ((FLUI_READER.getInt(0x0D0C) & 0x8) > 0) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean Strobe() {
		if ((FLUI_READER.getInt(0x0D0C) & 0x10) > 0) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean Wing() {
		if ((FLUI_READER.getInt(0x0D0C) & 0x80) > 0) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean Logo() {
		if ((FLUI_READER.getInt(0x0D0C) & 0x100) > 0) {
			return true;
		} else {
			return false;
		}
	}
}
